// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protocol/burst.proto

package burst.protocol;

/**
 * Protobuf enum {@code protocol.Headers}
 */
public enum Headers
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <pre>
   * 错误信息 string
   * </pre>
   *
   * <code>ERROR = 0;</code>
   */
  ERROR(0),
  /**
   * <pre>
   * port mapping Map&lt;int,Proxy&gt;
   * </pre>
   *
   * <code>PORTS = 1;</code>
   */
  PORTS(1),
  /**
   * <pre>
   * 服务端暴露的端口 int32
   * </pre>
   *
   * <code>SERVER_EXPORT_PORT = 2;</code>
   */
  SERVER_EXPORT_PORT(2),
  /**
   * <pre>
   * 外网客户的id string
   * </pre>
   *
   * <code>USER_CONNECT_ID = 3;</code>
   */
  USER_CONNECT_ID(3),
  /**
   * <pre>
   * register token string
   * </pre>
   *
   * <code>TOKEN = 4;</code>
   */
  TOKEN(4),
  UNRECOGNIZED(-1),
  ;

  /**
   * <pre>
   * 错误信息 string
   * </pre>
   *
   * <code>ERROR = 0;</code>
   */
  public static final int ERROR_VALUE = 0;
  /**
   * <pre>
   * port mapping Map&lt;int,Proxy&gt;
   * </pre>
   *
   * <code>PORTS = 1;</code>
   */
  public static final int PORTS_VALUE = 1;
  /**
   * <pre>
   * 服务端暴露的端口 int32
   * </pre>
   *
   * <code>SERVER_EXPORT_PORT = 2;</code>
   */
  public static final int SERVER_EXPORT_PORT_VALUE = 2;
  /**
   * <pre>
   * 外网客户的id string
   * </pre>
   *
   * <code>USER_CONNECT_ID = 3;</code>
   */
  public static final int USER_CONNECT_ID_VALUE = 3;
  /**
   * <pre>
   * register token string
   * </pre>
   *
   * <code>TOKEN = 4;</code>
   */
  public static final int TOKEN_VALUE = 4;


  public final int getNumber() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalArgumentException(
          "Can't get the number of an unknown enum value.");
    }
    return value;
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static Headers valueOf(int value) {
    return forNumber(value);
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   */
  public static Headers forNumber(int value) {
    switch (value) {
      case 0: return ERROR;
      case 1: return PORTS;
      case 2: return SERVER_EXPORT_PORT;
      case 3: return USER_CONNECT_ID;
      case 4: return TOKEN;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<Headers>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      Headers> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<Headers>() {
          public Headers findValueByNumber(int number) {
            return Headers.forNumber(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalStateException(
          "Can't get the descriptor of an unrecognized enum value.");
    }
    return getDescriptor().getValues().get(ordinal());
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return burst.protocol.Burst.getDescriptor().getEnumTypes().get(1);
  }

  private static final Headers[] VALUES = values();

  public static Headers valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    if (desc.getIndex() == -1) {
      return UNRECOGNIZED;
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private Headers(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:protocol.Headers)
}

