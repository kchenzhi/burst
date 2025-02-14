package main

import (
	"errors"
	"flag"
	burst "github.com/fzdwx/burst/burst-client/client"
	"github.com/fzdwx/burst/burst-client/common"
	"github.com/fzdwx/burst/burst-client/protocol"
	log "github.com/sirupsen/logrus"
	prefixed "github.com/x-cray/logrus-prefixed-formatter"
	"io/ioutil"
	"net/url"
	"os"
	"strings"
)

var (
	serverIp   = flag.String("sip", "localhost", "server ip")
	serverPort = flag.Int("sp", 10086, "server serverPort")
	token      = flag.String("t", "b92a205269d94d38808c3979615245eb", "your key, you can get it from server")
	usage      = flag.Bool("h", false, "help")
	debug      = flag.Bool("d", true, "log level use debug")
	serverAddr string
)

func init() {
	flag.Parse()
	if *usage {
		flag.Usage()
		os.Exit(0)
	}

	if strings.Compare(*token, "null") == 0 {
		log.Fatal("token is null")
		os.Exit(1)
	}

	//log.SetFormatter(&log.TextFormatter{
	//	ForceColors:     true,
	//	FullTimestamp:   true,
	//	TimestampFormat: "2006-01-02 15:04:05.000",
	//})
	formatter := new(prefixed.TextFormatter)
	formatter.FullTimestamp = true
	formatter.TimestampFormat = "2006-01-02 15:04:05.000"
	log.SetFormatter(formatter)

	if *debug {
		log.SetLevel(log.DebugLevel)
	} else {
		log.SetLevel(log.InfoLevel)
	}

	serverAddr = common.FormatToAddr(*serverIp, *serverPort)

	log.Infoln("log level:", common.WrapGreen(log.GetLevel().String()))
	log.Infoln("server address:", common.WrapGreen(serverAddr))
}

func main() {
	u := url.URL{Scheme: "ws", Host: serverAddr, Path: "/connect", RawQuery: "token=" + *token}
	client, resp, err := burst.Connect(u)
	if err != nil {
		body := resp.Body
		defer body.Close()
		data, _ := ioutil.ReadAll(body)
		log.Fatal(string(data))
	}
	defer client.Close()

	client.MountBinaryHandler(func(data []byte, client *burst.Client) {
		burstMessage, err := protocol.Decode(data)
		if err != nil {
			log.Error(err)
			return
		}

		switch burstMessage.Type {
		case protocol.BurstType_ADD_PROXY_INFO:
			handlerAddProxyInfo(burstMessage, client)
		case protocol.BurstType_USER_CONNECT:
			handlerUserConnect(burstMessage, client)
		case protocol.BurstType_FORWARD_DATA:
			handlerForwardData(burstMessage, client)
		case protocol.BurstType_REMOVE_PROXY_INFO:
			handlerRemoveProxyInfo(burstMessage, client)
		}
	})

	down := make(chan byte)
	go func() {
		defer close(down)
		client.React()
	}()

	for {
		select {
		case <-down:
			return
		}
	}
}

// handlerAddProxyInfo 处理添加映射信息
func handlerAddProxyInfo(message *protocol.BurstMessage, client *burst.Client) {
	err := protocol.GetError(message)
	if err != nil {
		client.Over(errors.New("init error " + err.Error()))
	}

	ports, err := protocol.GetPorts(message)
	if err != nil {
		client.Over(errors.New("init get ports error " + err.Error()))
	}

	proxyInfo := ports.GetPorts()
	client.AddProxyInfo(proxyInfo)

	for serverExportPort, proxy := range proxyInfo {
		log.Infof("add proxy: %s to server %s", common.WrapRed(proxy.Host()), common.WrapRed(common.FormatToAddr(*serverIp, int(serverExportPort))))
	}
}

// handlerRemoveProxyInfo 移除映射信息
func handlerRemoveProxyInfo(message *protocol.BurstMessage, client *burst.Client) {
	err := protocol.GetError(message)
	if err != nil {
		client.Over(errors.New("init error " + err.Error()))
	}

	port := message.GetServerPort()
	if len(port) == 0 {
		return
	}

	client.RemoveProxyPorts(port)
}

func handlerUserConnect(message *protocol.BurstMessage, client *burst.Client) {
	serverExportPort, err := protocol.GetServerExportPort(message)
	if err != nil {
		log.Error("parse server export port error ", err)
		return
	}

	proxy, ok := client.GetProxy(serverExportPort)
	if !ok {
		log.Error("local port not found ", serverExportPort)
		return
	}

	userConnectId, err := protocol.GetUserConnectId(message)
	if err != nil {
		log.Error("parse user connect id error ", err)
		return
	}

	userConnForward, err := burst.NewUserConn(serverExportPort, proxy, userConnectId)
	if err != nil {
		log.Error("local port connect error ", err)
		return
	}

	// step 5 [forwarded to the server], and then forwarded to a specific user
	go func() {
		defer func() {
			if err := recover(); err != nil {
				log.Error("forward to server: recover ", err)
			}
		}()
		userConnForward.React(client)
	}()
}

func handlerForwardData(message *protocol.BurstMessage, client *burst.Client) {
	// step 4 [forward to local port]
	burst.Fw.ToLocal(message)
}
