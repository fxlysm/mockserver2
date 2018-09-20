# 简介

**标题**：测试桩相关APIs

**简介**：代码：http://git.imovie.cn/test/mockserver

**HOST**:liuyong:9025

**联系人**:liuyong@imovie.cn

**接口路径**：/v2/api-docs?group=微信支付
# 微信支付api

## 关闭订单

**接口说明**:


**接口地址**:`/pay/closeorder`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`

**请求参数**：

| 参数名称| 说明 |  in |  是否必须|  类型|  schema|
| -------- | ----------------- |-----------|--------|----|--- |
|closeOrder| closeOrder  | body | true |CloseOrder  | CloseOrder   |

**schema属性说明**

**CloseOrder**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|appid| appid  | body | true |string  |    |
|mch_id| 商户ID  | body | true |string  |    |
|nonce_str| 随机字符串  | body | true |string  |    |
|out_trade_no| 商户订单号  | body | true |string  |    |
|sign| 签名  | body | true |string  |    |
|sign_type| 签名类型  | body | false |string  |    |
**响应数据**:

```json

```
**响应参数说明**:

暂无



**响应状态码说明**:

| 状态码         | 说明|    schema|
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 对账单下载

**接口说明**:


**接口地址**:`/pay/downloadbill`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`

**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|appid| appid  | query | true |string  |    |
|mch_id| mch_id  | query | true |string  |    |
|map| map  | query | true |ref  |    |
|out| out  | body | false |PrintWriter  | PrintWriter   |
**响应数据**:

```json

```
**响应参数说明**:

暂无



**响应状态码说明**:

| 状态码         | 说明|    schema|
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 下载资金账单

**接口说明**:


**接口地址**:`/pay/downloadfundflow`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`

**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|appid| appid  | query | true |string  |    |
|mch_id| mch_id  | query | true |string  |    |
|map| map  | query | true |ref  |    |
|out| out  | body | false |PrintWriter  | PrintWriter   |
**响应数据**:

```json

```
**响应参数说明**:

暂无



**响应状态码说明**:

| 状态码         | 说明|    schema|
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 订单查询

**接口说明**:


**接口地址**:`/pay/orderquery`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`

**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|orderquery| orderquery  | body | true |Orderquery  | Orderquery   |
**schema属性说明**

**Orderquery**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|appid| appid  | body | true |string  |    |
|mch_id| 商户ID  | body | true |string  |    |
|nonce_str| 随机字符串  | body | true |string  |    |
|out_trade_no| 商户订单号  | body | true |string  |    |
|sign| 签名  | body | true |string  |    |
|sign_type| 签名类型  | body | false |string  |    |
|transaction_id| 微信订单号  | body | true |string  |    |
**响应数据**:

```json

```
**响应参数说明**:

暂无



**响应状态码说明**:

| 状态码         | 说明|    schema|
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 退款查询

**接口说明**:


**接口地址**:`/pay/refundquery`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`

**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|refoundRes| refoundRes  | body | true |RefoundRes  | RefoundRes   |
**schema属性说明**

**RefoundRes**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|appid| appid  | body | true |string  |    |
|mch_id| 商户ID  | body | true |string  |    |
|nonce_str| 随机字符串  | body | true |string  |    |
|notify_url| 退款结果通知url  | body | false |string  |    |
|out_trade_no| 商户订单号  | body | true |string  |    |
|refund_account| 退款资金来源  | body | false |string  |    |
|refund_desc| 退款原因  | body | false |string  |    |
|refund_fee| 退款金额  | body | true |string  |    |
|refund_fee_type| 退款货币种类  | body | false |string  |    |
|sign| 签名  | body | true |string  |    |
|sign_type| 签名类型  | body | false |string  |    |
|total_fee| 订单金额  | body | true |string  |    |
|transaction_id| 微信订单号  | body | true |string  |    |
**响应数据**:

```json

```
**响应参数说明**:

暂无



**响应状态码说明**:

| 状态码         | 说明|    schema|
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
## 统一下单【入参为xml格式，这里调试使用json格式】

**接口说明**:


**接口地址**:`/pay/unifiedorder`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["application/xml"]`

**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|payRequest| payRequest  | body | true |PayRequest  | PayRequest   |
**schema属性说明**

**PayRequest**

| 参数名称         | 说明    |     in |  是否必须   |  类型  |  schema |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|appid| appid  | body | true |string  |    |
|body| 商品描述  | body | true |string  |    |
|mch_id| 商户ID  | body | true |string  |    |
|nonce_str| 随机字符串  | body | true |string  |    |
|notify_url| 支付通知  | body | true |string  |    |
|openid| 用户标识openid  | body | false |string  |    |
|out_trade_no| 商户交易流水号  | body | true |string  |    |
|sign| 签名  | body | true |string  |    |
|spbill_create_ip| 终端IP  | body | true |string  |    |
|total_fee| 交易额  | body | true |string  |    |
|trade_type| 业务类型  | body | true |string  |    |
**响应数据**:

```json

```
**响应参数说明**:

暂无



**响应状态码说明**:

| 状态码         | 说明|    schema|
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
# 支付通知
## 模拟客户端接收支付通知

**接口说明**:


**接口地址**:`/pay/handlerNotify`


**请求方式**：`POST`


**consumes**:`["application/json"]`


**produces**:`["*/*"]`

**请求参数**：

| 参数名称         | 说明     |     in |  是否必须      |  类型   |  schema  |
| ------------ | -------------------------------- |-----------|--------|----|--- |
|map| map  | query | true |ref  |    |
|str| str  | body | true |string  |    |
|out| out  | body | false |PrintWriter  | PrintWriter   |
**响应数据**:

```json

```
**响应参数说明**:

暂无



**响应状态码说明**:

| 状态码         | 说明|    schema|
| ------------ | -------------------------------- |---------------------- |
| 200 | OK  ||
| 201 | Created  ||
| 401 | Unauthorized  ||
| 403 | Forbidden  ||
| 404 | Not Found  ||
