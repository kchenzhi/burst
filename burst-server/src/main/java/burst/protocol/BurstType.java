// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protocol/burst.proto

package burst.protocol;

/**
 * Protobuf enum {@code protocol.BurstType}
 */
public enum BurstType
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <pre>
   * 添加代理信息
   * </pre>
   *
   * <code>ADD_PROXY_INFO = 0;</code>
   */
  ADD_PROXY_INFO(0),
  /**
   * <pre>
   * 关闭
   * </pre>
   *
   * <code>CLOSE = 1;</code>
   */
  CLOSE(1),
  /**
   * <pre>
   * 客户连接
   * </pre>
   *
   * <code>USER_CONNECT = 2;</code>
   */
  USER_CONNECT(2),
  /**
   * <pre>
   * 转发数据
   * </pre>
   *
   * <code>FORWARD_DATA = 3;</code>
   */
  FORWARD_DATA(3),
  UNRECOGNIZED(-1),
  ;

  /**
   * <pre>
   * 添加代理信息
   * </pre>
   *
   * <code>ADD_PROXY_INFO = 0;</code>
   */
  public static final int ADD_PROXY_INFO_VALUE = 0;
  /**
   * <pre>
   * 关闭
   * </pre>
   *
   * <code>CLOSE = 1;</code>
   */
  public static final int CLOSE_VALUE = 1;
  /**
   * <pre>
   * 客户连接
   * </pre>
   *
   * <code>USER_CONNECT = 2;</code>
   */
  public static final int USER_CONNECT_VALUE = 2;
  /**
   * <pre>
   * 转发数据
   * </pre>
   *
   * <code>FORWARD_DATA = 3;</code>
   */
  public static final int FORWARD_DATA_VALUE = 3;


  public final int getNumber() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalArgumentException(
          "Can't get the number of an unknown enum value.");
    }
    return value;
  }

  /**
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static BurstType valueOf(int value) {
    return forNumber(value);
  }

  public static BurstType forNumber(int value) {
    switch (value) {
      case 0: return ADD_PROXY_INFO;
      case 1: return CLOSE;
      case 2: return USER_CONNECT;
      case 3: return FORWARD_DATA;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<BurstType>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      BurstType> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<BurstType>() {
          public BurstType findValueByNumber(int number) {
            return BurstType.forNumber(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    return getDescriptor().getValues().get(ordinal());
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return burst.protocol.Burst.getDescriptor().getEnumTypes().get(0);
  }

  private static final BurstType[] VALUES = values();

  public static BurstType valueOf(
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

  private BurstType(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:protocol.BurstType)
}

