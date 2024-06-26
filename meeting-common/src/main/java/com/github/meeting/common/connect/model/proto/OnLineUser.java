// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: OnlineUser.proto

package com.github.meeting.common.connect.model.proto;

public final class OnLineUser {
  private OnLineUser() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface UserSearchOrBuilder extends
      // @@protoc_insertion_point(interface_extends:com.github.meeting.common.connect.model.proto.UserSearch)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>repeated .com.github.meeting.common.connect.model.proto.AccountInfo accounts = 1;</code>
     */
    java.util.List<com.github.meeting.common.connect.model.proto.Account.AccountInfo> 
        getAccountsList();
    /**
     * <code>repeated .com.github.meeting.common.connect.model.proto.AccountInfo accounts = 1;</code>
     */
    com.github.meeting.common.connect.model.proto.Account.AccountInfo getAccounts(int index);
    /**
     * <code>repeated .com.github.meeting.common.connect.model.proto.AccountInfo accounts = 1;</code>
     */
    int getAccountsCount();
    /**
     * <code>repeated .com.github.meeting.common.connect.model.proto.AccountInfo accounts = 1;</code>
     */
    java.util.List<? extends com.github.meeting.common.connect.model.proto.Account.AccountInfoOrBuilder> 
        getAccountsOrBuilderList();
    /**
     * <code>repeated .com.github.meeting.common.connect.model.proto.AccountInfo accounts = 1;</code>
     */
    com.github.meeting.common.connect.model.proto.Account.AccountInfoOrBuilder getAccountsOrBuilder(
        int index);
  }
  /**
   * Protobuf type {@code com.github.meeting.common.connect.model.proto.UserSearch}
   */
  public static final class UserSearch extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:com.github.meeting.common.connect.model.proto.UserSearch)
      UserSearchOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use UserSearch.newBuilder() to construct.
    private UserSearch(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private UserSearch() {
      accounts_ = java.util.Collections.emptyList();
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new UserSearch();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.github.meeting.common.connect.model.proto.OnLineUser.internal_static_com_github_meeting_common_connect_model_proto_UserSearch_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.github.meeting.common.connect.model.proto.OnLineUser.internal_static_com_github_meeting_common_connect_model_proto_UserSearch_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.github.meeting.common.connect.model.proto.OnLineUser.UserSearch.class, com.github.meeting.common.connect.model.proto.OnLineUser.UserSearch.Builder.class);
    }

    public static final int ACCOUNTS_FIELD_NUMBER = 1;
    @SuppressWarnings("serial")
    private java.util.List<com.github.meeting.common.connect.model.proto.Account.AccountInfo> accounts_;
    /**
     * <code>repeated .com.github.meeting.common.connect.model.proto.AccountInfo accounts = 1;</code>
     */
    @java.lang.Override
    public java.util.List<com.github.meeting.common.connect.model.proto.Account.AccountInfo> getAccountsList() {
      return accounts_;
    }
    /**
     * <code>repeated .com.github.meeting.common.connect.model.proto.AccountInfo accounts = 1;</code>
     */
    @java.lang.Override
    public java.util.List<? extends com.github.meeting.common.connect.model.proto.Account.AccountInfoOrBuilder> 
        getAccountsOrBuilderList() {
      return accounts_;
    }
    /**
     * <code>repeated .com.github.meeting.common.connect.model.proto.AccountInfo accounts = 1;</code>
     */
    @java.lang.Override
    public int getAccountsCount() {
      return accounts_.size();
    }
    /**
     * <code>repeated .com.github.meeting.common.connect.model.proto.AccountInfo accounts = 1;</code>
     */
    @java.lang.Override
    public com.github.meeting.common.connect.model.proto.Account.AccountInfo getAccounts(int index) {
      return accounts_.get(index);
    }
    /**
     * <code>repeated .com.github.meeting.common.connect.model.proto.AccountInfo accounts = 1;</code>
     */
    @java.lang.Override
    public com.github.meeting.common.connect.model.proto.Account.AccountInfoOrBuilder getAccountsOrBuilder(
        int index) {
      return accounts_.get(index);
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      for (int i = 0; i < accounts_.size(); i++) {
        output.writeMessage(1, accounts_.get(i));
      }
      getUnknownFields().writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      for (int i = 0; i < accounts_.size(); i++) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(1, accounts_.get(i));
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.github.meeting.common.connect.model.proto.OnLineUser.UserSearch)) {
        return super.equals(obj);
      }
      com.github.meeting.common.connect.model.proto.OnLineUser.UserSearch other = (com.github.meeting.common.connect.model.proto.OnLineUser.UserSearch) obj;

      if (!getAccountsList()
          .equals(other.getAccountsList())) return false;
      if (!getUnknownFields().equals(other.getUnknownFields())) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      if (getAccountsCount() > 0) {
        hash = (37 * hash) + ACCOUNTS_FIELD_NUMBER;
        hash = (53 * hash) + getAccountsList().hashCode();
      }
      hash = (29 * hash) + getUnknownFields().hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.github.meeting.common.connect.model.proto.OnLineUser.UserSearch parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.github.meeting.common.connect.model.proto.OnLineUser.UserSearch parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.github.meeting.common.connect.model.proto.OnLineUser.UserSearch parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.github.meeting.common.connect.model.proto.OnLineUser.UserSearch parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.github.meeting.common.connect.model.proto.OnLineUser.UserSearch parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.github.meeting.common.connect.model.proto.OnLineUser.UserSearch parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.github.meeting.common.connect.model.proto.OnLineUser.UserSearch parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.github.meeting.common.connect.model.proto.OnLineUser.UserSearch parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.github.meeting.common.connect.model.proto.OnLineUser.UserSearch parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.github.meeting.common.connect.model.proto.OnLineUser.UserSearch parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.github.meeting.common.connect.model.proto.OnLineUser.UserSearch parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.github.meeting.common.connect.model.proto.OnLineUser.UserSearch parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.github.meeting.common.connect.model.proto.OnLineUser.UserSearch prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code com.github.meeting.common.connect.model.proto.UserSearch}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:com.github.meeting.common.connect.model.proto.UserSearch)
        com.github.meeting.common.connect.model.proto.OnLineUser.UserSearchOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.github.meeting.common.connect.model.proto.OnLineUser.internal_static_com_github_meeting_common_connect_model_proto_UserSearch_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.github.meeting.common.connect.model.proto.OnLineUser.internal_static_com_github_meeting_common_connect_model_proto_UserSearch_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.github.meeting.common.connect.model.proto.OnLineUser.UserSearch.class, com.github.meeting.common.connect.model.proto.OnLineUser.UserSearch.Builder.class);
      }

      // Construct using com.github.meeting.common.connect.model.proto.OnLineUser.UserSearch.newBuilder()
      private Builder() {

      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);

      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        bitField0_ = 0;
        if (accountsBuilder_ == null) {
          accounts_ = java.util.Collections.emptyList();
        } else {
          accounts_ = null;
          accountsBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.github.meeting.common.connect.model.proto.OnLineUser.internal_static_com_github_meeting_common_connect_model_proto_UserSearch_descriptor;
      }

      @java.lang.Override
      public com.github.meeting.common.connect.model.proto.OnLineUser.UserSearch getDefaultInstanceForType() {
        return com.github.meeting.common.connect.model.proto.OnLineUser.UserSearch.getDefaultInstance();
      }

      @java.lang.Override
      public com.github.meeting.common.connect.model.proto.OnLineUser.UserSearch build() {
        com.github.meeting.common.connect.model.proto.OnLineUser.UserSearch result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.github.meeting.common.connect.model.proto.OnLineUser.UserSearch buildPartial() {
        com.github.meeting.common.connect.model.proto.OnLineUser.UserSearch result = new com.github.meeting.common.connect.model.proto.OnLineUser.UserSearch(this);
        buildPartialRepeatedFields(result);
        if (bitField0_ != 0) { buildPartial0(result); }
        onBuilt();
        return result;
      }

      private void buildPartialRepeatedFields(com.github.meeting.common.connect.model.proto.OnLineUser.UserSearch result) {
        if (accountsBuilder_ == null) {
          if (((bitField0_ & 0x00000001) != 0)) {
            accounts_ = java.util.Collections.unmodifiableList(accounts_);
            bitField0_ = (bitField0_ & ~0x00000001);
          }
          result.accounts_ = accounts_;
        } else {
          result.accounts_ = accountsBuilder_.build();
        }
      }

      private void buildPartial0(com.github.meeting.common.connect.model.proto.OnLineUser.UserSearch result) {
        int from_bitField0_ = bitField0_;
      }

      @java.lang.Override
      public Builder clone() {
        return super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.github.meeting.common.connect.model.proto.OnLineUser.UserSearch) {
          return mergeFrom((com.github.meeting.common.connect.model.proto.OnLineUser.UserSearch)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.github.meeting.common.connect.model.proto.OnLineUser.UserSearch other) {
        if (other == com.github.meeting.common.connect.model.proto.OnLineUser.UserSearch.getDefaultInstance()) return this;
        if (accountsBuilder_ == null) {
          if (!other.accounts_.isEmpty()) {
            if (accounts_.isEmpty()) {
              accounts_ = other.accounts_;
              bitField0_ = (bitField0_ & ~0x00000001);
            } else {
              ensureAccountsIsMutable();
              accounts_.addAll(other.accounts_);
            }
            onChanged();
          }
        } else {
          if (!other.accounts_.isEmpty()) {
            if (accountsBuilder_.isEmpty()) {
              accountsBuilder_.dispose();
              accountsBuilder_ = null;
              accounts_ = other.accounts_;
              bitField0_ = (bitField0_ & ~0x00000001);
              accountsBuilder_ = 
                com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                   getAccountsFieldBuilder() : null;
            } else {
              accountsBuilder_.addAllMessages(other.accounts_);
            }
          }
        }
        this.mergeUnknownFields(other.getUnknownFields());
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        if (extensionRegistry == null) {
          throw new java.lang.NullPointerException();
        }
        try {
          boolean done = false;
          while (!done) {
            int tag = input.readTag();
            switch (tag) {
              case 0:
                done = true;
                break;
              case 10: {
                com.github.meeting.common.connect.model.proto.Account.AccountInfo m =
                    input.readMessage(
                        com.github.meeting.common.connect.model.proto.Account.AccountInfo.parser(),
                        extensionRegistry);
                if (accountsBuilder_ == null) {
                  ensureAccountsIsMutable();
                  accounts_.add(m);
                } else {
                  accountsBuilder_.addMessage(m);
                }
                break;
              } // case 10
              default: {
                if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                  done = true; // was an endgroup tag
                }
                break;
              } // default:
            } // switch (tag)
          } // while (!done)
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw e.unwrapIOException();
        } finally {
          onChanged();
        } // finally
        return this;
      }
      private int bitField0_;

      private java.util.List<com.github.meeting.common.connect.model.proto.Account.AccountInfo> accounts_ =
        java.util.Collections.emptyList();
      private void ensureAccountsIsMutable() {
        if (!((bitField0_ & 0x00000001) != 0)) {
          accounts_ = new java.util.ArrayList<com.github.meeting.common.connect.model.proto.Account.AccountInfo>(accounts_);
          bitField0_ |= 0x00000001;
         }
      }

      private com.google.protobuf.RepeatedFieldBuilderV3<
          com.github.meeting.common.connect.model.proto.Account.AccountInfo, com.github.meeting.common.connect.model.proto.Account.AccountInfo.Builder, com.github.meeting.common.connect.model.proto.Account.AccountInfoOrBuilder> accountsBuilder_;

      /**
       * <code>repeated .com.github.meeting.common.connect.model.proto.AccountInfo accounts = 1;</code>
       */
      public java.util.List<com.github.meeting.common.connect.model.proto.Account.AccountInfo> getAccountsList() {
        if (accountsBuilder_ == null) {
          return java.util.Collections.unmodifiableList(accounts_);
        } else {
          return accountsBuilder_.getMessageList();
        }
      }
      /**
       * <code>repeated .com.github.meeting.common.connect.model.proto.AccountInfo accounts = 1;</code>
       */
      public int getAccountsCount() {
        if (accountsBuilder_ == null) {
          return accounts_.size();
        } else {
          return accountsBuilder_.getCount();
        }
      }
      /**
       * <code>repeated .com.github.meeting.common.connect.model.proto.AccountInfo accounts = 1;</code>
       */
      public com.github.meeting.common.connect.model.proto.Account.AccountInfo getAccounts(int index) {
        if (accountsBuilder_ == null) {
          return accounts_.get(index);
        } else {
          return accountsBuilder_.getMessage(index);
        }
      }
      /**
       * <code>repeated .com.github.meeting.common.connect.model.proto.AccountInfo accounts = 1;</code>
       */
      public Builder setAccounts(
          int index, com.github.meeting.common.connect.model.proto.Account.AccountInfo value) {
        if (accountsBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureAccountsIsMutable();
          accounts_.set(index, value);
          onChanged();
        } else {
          accountsBuilder_.setMessage(index, value);
        }
        return this;
      }
      /**
       * <code>repeated .com.github.meeting.common.connect.model.proto.AccountInfo accounts = 1;</code>
       */
      public Builder setAccounts(
          int index, com.github.meeting.common.connect.model.proto.Account.AccountInfo.Builder builderForValue) {
        if (accountsBuilder_ == null) {
          ensureAccountsIsMutable();
          accounts_.set(index, builderForValue.build());
          onChanged();
        } else {
          accountsBuilder_.setMessage(index, builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .com.github.meeting.common.connect.model.proto.AccountInfo accounts = 1;</code>
       */
      public Builder addAccounts(com.github.meeting.common.connect.model.proto.Account.AccountInfo value) {
        if (accountsBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureAccountsIsMutable();
          accounts_.add(value);
          onChanged();
        } else {
          accountsBuilder_.addMessage(value);
        }
        return this;
      }
      /**
       * <code>repeated .com.github.meeting.common.connect.model.proto.AccountInfo accounts = 1;</code>
       */
      public Builder addAccounts(
          int index, com.github.meeting.common.connect.model.proto.Account.AccountInfo value) {
        if (accountsBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureAccountsIsMutable();
          accounts_.add(index, value);
          onChanged();
        } else {
          accountsBuilder_.addMessage(index, value);
        }
        return this;
      }
      /**
       * <code>repeated .com.github.meeting.common.connect.model.proto.AccountInfo accounts = 1;</code>
       */
      public Builder addAccounts(
          com.github.meeting.common.connect.model.proto.Account.AccountInfo.Builder builderForValue) {
        if (accountsBuilder_ == null) {
          ensureAccountsIsMutable();
          accounts_.add(builderForValue.build());
          onChanged();
        } else {
          accountsBuilder_.addMessage(builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .com.github.meeting.common.connect.model.proto.AccountInfo accounts = 1;</code>
       */
      public Builder addAccounts(
          int index, com.github.meeting.common.connect.model.proto.Account.AccountInfo.Builder builderForValue) {
        if (accountsBuilder_ == null) {
          ensureAccountsIsMutable();
          accounts_.add(index, builderForValue.build());
          onChanged();
        } else {
          accountsBuilder_.addMessage(index, builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .com.github.meeting.common.connect.model.proto.AccountInfo accounts = 1;</code>
       */
      public Builder addAllAccounts(
          java.lang.Iterable<? extends com.github.meeting.common.connect.model.proto.Account.AccountInfo> values) {
        if (accountsBuilder_ == null) {
          ensureAccountsIsMutable();
          com.google.protobuf.AbstractMessageLite.Builder.addAll(
              values, accounts_);
          onChanged();
        } else {
          accountsBuilder_.addAllMessages(values);
        }
        return this;
      }
      /**
       * <code>repeated .com.github.meeting.common.connect.model.proto.AccountInfo accounts = 1;</code>
       */
      public Builder clearAccounts() {
        if (accountsBuilder_ == null) {
          accounts_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000001);
          onChanged();
        } else {
          accountsBuilder_.clear();
        }
        return this;
      }
      /**
       * <code>repeated .com.github.meeting.common.connect.model.proto.AccountInfo accounts = 1;</code>
       */
      public Builder removeAccounts(int index) {
        if (accountsBuilder_ == null) {
          ensureAccountsIsMutable();
          accounts_.remove(index);
          onChanged();
        } else {
          accountsBuilder_.remove(index);
        }
        return this;
      }
      /**
       * <code>repeated .com.github.meeting.common.connect.model.proto.AccountInfo accounts = 1;</code>
       */
      public com.github.meeting.common.connect.model.proto.Account.AccountInfo.Builder getAccountsBuilder(
          int index) {
        return getAccountsFieldBuilder().getBuilder(index);
      }
      /**
       * <code>repeated .com.github.meeting.common.connect.model.proto.AccountInfo accounts = 1;</code>
       */
      public com.github.meeting.common.connect.model.proto.Account.AccountInfoOrBuilder getAccountsOrBuilder(
          int index) {
        if (accountsBuilder_ == null) {
          return accounts_.get(index);  } else {
          return accountsBuilder_.getMessageOrBuilder(index);
        }
      }
      /**
       * <code>repeated .com.github.meeting.common.connect.model.proto.AccountInfo accounts = 1;</code>
       */
      public java.util.List<? extends com.github.meeting.common.connect.model.proto.Account.AccountInfoOrBuilder> 
           getAccountsOrBuilderList() {
        if (accountsBuilder_ != null) {
          return accountsBuilder_.getMessageOrBuilderList();
        } else {
          return java.util.Collections.unmodifiableList(accounts_);
        }
      }
      /**
       * <code>repeated .com.github.meeting.common.connect.model.proto.AccountInfo accounts = 1;</code>
       */
      public com.github.meeting.common.connect.model.proto.Account.AccountInfo.Builder addAccountsBuilder() {
        return getAccountsFieldBuilder().addBuilder(
            com.github.meeting.common.connect.model.proto.Account.AccountInfo.getDefaultInstance());
      }
      /**
       * <code>repeated .com.github.meeting.common.connect.model.proto.AccountInfo accounts = 1;</code>
       */
      public com.github.meeting.common.connect.model.proto.Account.AccountInfo.Builder addAccountsBuilder(
          int index) {
        return getAccountsFieldBuilder().addBuilder(
            index, com.github.meeting.common.connect.model.proto.Account.AccountInfo.getDefaultInstance());
      }
      /**
       * <code>repeated .com.github.meeting.common.connect.model.proto.AccountInfo accounts = 1;</code>
       */
      public java.util.List<com.github.meeting.common.connect.model.proto.Account.AccountInfo.Builder> 
           getAccountsBuilderList() {
        return getAccountsFieldBuilder().getBuilderList();
      }
      private com.google.protobuf.RepeatedFieldBuilderV3<
          com.github.meeting.common.connect.model.proto.Account.AccountInfo, com.github.meeting.common.connect.model.proto.Account.AccountInfo.Builder, com.github.meeting.common.connect.model.proto.Account.AccountInfoOrBuilder> 
          getAccountsFieldBuilder() {
        if (accountsBuilder_ == null) {
          accountsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
              com.github.meeting.common.connect.model.proto.Account.AccountInfo, com.github.meeting.common.connect.model.proto.Account.AccountInfo.Builder, com.github.meeting.common.connect.model.proto.Account.AccountInfoOrBuilder>(
                  accounts_,
                  ((bitField0_ & 0x00000001) != 0),
                  getParentForChildren(),
                  isClean());
          accounts_ = null;
        }
        return accountsBuilder_;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:com.github.meeting.common.connect.model.proto.UserSearch)
    }

    // @@protoc_insertion_point(class_scope:com.github.meeting.common.connect.model.proto.UserSearch)
    private static final com.github.meeting.common.connect.model.proto.OnLineUser.UserSearch DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.github.meeting.common.connect.model.proto.OnLineUser.UserSearch();
    }

    public static com.github.meeting.common.connect.model.proto.OnLineUser.UserSearch getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<UserSearch>
        PARSER = new com.google.protobuf.AbstractParser<UserSearch>() {
      @java.lang.Override
      public UserSearch parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        Builder builder = newBuilder();
        try {
          builder.mergeFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw e.setUnfinishedMessage(builder.buildPartial());
        } catch (com.google.protobuf.UninitializedMessageException e) {
          throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
        } catch (java.io.IOException e) {
          throw new com.google.protobuf.InvalidProtocolBufferException(e)
              .setUnfinishedMessage(builder.buildPartial());
        }
        return builder.buildPartial();
      }
    };

    public static com.google.protobuf.Parser<UserSearch> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<UserSearch> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.github.meeting.common.connect.model.proto.OnLineUser.UserSearch getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_github_meeting_common_connect_model_proto_UserSearch_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_com_github_meeting_common_connect_model_proto_UserSearch_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\020OnlineUser.proto\022-com.github.meeting.c" +
      "ommon.connect.model.proto\032\rAccount.proto" +
      "\"Z\n\nUserSearch\022L\n\010accounts\030\001 \003(\0132:.com.g" +
      "ithub.meeting.common.connect.model.proto" +
      ".AccountInfoB;\n-com.github.meeting.commo" +
      "n.connect.model.protoB\nOnLineUserb\006proto" +
      "3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.github.meeting.common.connect.model.proto.Account.getDescriptor(),
        });
    internal_static_com_github_meeting_common_connect_model_proto_UserSearch_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_github_meeting_common_connect_model_proto_UserSearch_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_com_github_meeting_common_connect_model_proto_UserSearch_descriptor,
        new java.lang.String[] { "Accounts", });
    com.github.meeting.common.connect.model.proto.Account.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
