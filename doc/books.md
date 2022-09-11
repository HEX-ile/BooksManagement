---
title: books v1.0.0
language_tabs:
  - shell: Shell
  - http: HTTP
  - javascript: JavaScript
  - ruby: Ruby
  - python: Python
  - php: PHP
  - java: Java
  - go: Go
toc_footers: []
includes: []
search: true
code_clipboard: true
highlight_theme: darkula
headingLevel: 2
generator: "@tarslib/widdershins v4.0.11"

---

# books

> v1.0.0

# 书籍Controller

<a id="opIdgetBookListUsingGET"></a>

## GET 获取书籍列表

GET /bm/book/books

### 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|author|query|string| 否 |author|
|bookClass|query|string| 否 |书籍类别（非必填）|
|bookshelfNumber|query|string| 否 |bookshelfNumber|
|current|query|string| 否 |当前页，默认为1|
|name|query|string| 否 |书名（非必填）|
|size|query|string| 否 |一页展示条数，默认为10|
|Bm-Token|header|string| 否 |登录sessionID|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[Reply](#schemareply)|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|Inline|

### 返回数据结构

<a id="opIdcreateBookUsingPOST"></a>

## POST 新增书籍

POST /bm/book/books

> Body 请求参数

```json
{
  "author": "string",
  "book_class": "string",
  "book_number": "string",
  "bookshelf_number": "string",
  "name": "string",
  "over": "Integer",
  "publishing_house": "string"
}
```

### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|Bm-Token|header|string| 否 ||sessionID|
|body|body|[json$jgjjeSbUNz](#schemajson%24jgjjesbunz)| 否 | json$jgjjeSbUNz|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[Reply](#schemareply)|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|Created|Inline|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|Inline|

### 返回数据结构

<a id="opIddeleteByIdUsingDELETE"></a>

## DELETE 通过ID删除书籍

DELETE /bm/book/books/{id}

### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|id|path|integer| 是 ||id|
|Bm-Token|header|string| 否 ||sessionID|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[Reply](#schemareply)|
|204|[No Content](https://tools.ietf.org/html/rfc7231#section-6.3.5)|No Content|Inline|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|Inline|

### 返回数据结构

<a id="opIdgetBookByIdUsingGET"></a>

## GET 获取书籍详细信息

GET /bm/book/books/{id}

### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|id|path|string| 是 ||书籍ID|
|Bm-Token|header|string| 否 ||sessionID|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[Reply](#schemareply)|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|Inline|

### 返回数据结构

<a id="opIdmodifyInfoByIdUsingPUT"></a>

## PUT 修改书籍信息

PUT /bm/book/books/{id}

> Body 请求参数

```json
{
  "author": "string",
  "book_class": "string",
  "book_number": "string",
  "bookshelf_number": "string",
  "name": "string",
  "publishing_house": "string"
}
```

### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|id|path|integer| 是 ||id|
|Bm-Token|header|string| 否 ||sessionID|
|body|body|[json$NmcsOLlgWf](#schemajson%24nmcsollgwf)| 否 | json$NmcsOLlgWf|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[Reply](#schemareply)|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|Created|Inline|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|Inline|

### 返回数据结构

# 借阅Controller

<a id="opIdborrowBookUsingPOST"></a>

## POST 借用书籍

POST /bm/borrow/borrow_book

> Body 请求参数

```json
{
  "bookId": "Integeer",
  "borrowTime": "2022-09-11",
  "returnTime": "2022-09-11"
}
```

### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|Bm-Token|header|string| 否 ||sessionID|
|body|body|object| 否 ||none|
|» bookId|body|string| 否 ||书籍ID|
|» borrowTime|body|string| 否 ||借书日期|
|» returnTime|body|string| 否 ||还书日期|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[Reply](#schemareply)|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|Created|Inline|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|Inline|

### 返回数据结构

<a id="opIdreturnBookUsingPOST"></a>

## POST 归还书籍

POST /bm/borrow/return_book

> Body 请求参数

```json
{
  "bookId": "string"
}
```

### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|Bm-Token|header|string| 否 ||sessionID|
|body|body|[json$KHRJDsPQaL](#schemajson%24khrjdspqal)| 否 | json$KHRJDsPQaL|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[Reply](#schemareply)|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|Created|Inline|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|Inline|

### 返回数据结构

# 用户Controller

<a id="opIdloginUserNameUsingPOST"></a>

## POST 账号密码登录校验

POST /bm/user/login_username

> Body 请求参数

```json
{
  "password": "string",
  "username": "string"
}
```

### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|Bm-Token|header|string| 否 ||sessionID|
|body|body|[json$SPSlZapVgB](#schemajson%24spslzapvgb)| 否 | json$SPSlZapVgB|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[Reply](#schemareply)|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|Created|Inline|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|Inline|

### 返回数据结构

<a id="opIdregisterUsingPOST"></a>

## POST 用户注册

POST /bm/user/user_register

> Body 请求参数

```json
{
  "username": "string",
  "password": "string",
  "mobile": "string",
  "sex": 0,
  "code": "string"
}
```

### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|Bm-Token|header|string| 否 ||sessionID|
|body|body|object| 否 ||none|
|» username|body|string| 是 ||用户名或手机号|
|» password|body|string| 是 ||登录密码|
|» mobile|body|string| 是 ||none|
|» sex|body|integer| 是 ||none|
|» code|body|string| 是 ||none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[Reply](#schemareply)|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|Created|Inline|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|Inline|

### 返回数据结构

<a id="opIdgetBookListUsingGET_1"></a>

## GET 获取用户列表

GET /bm/user/user

### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|current|query|string| 否 ||当前页，默认为1|
|size|query|string| 否 ||一页展示条数，默认为10|
|Bm-Token|header|string| 否 ||sessionID|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[Reply](#schemareply)|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|Inline|

### 返回数据结构

<a id="opIdmodifyUserInfoUsingPUT"></a>

## PUT 修改基本资料

PUT /bm/user/modify_user_info

> Body 请求参数

```json
{
  "code": "string",
  "mobile": "string",
  "password": "string",
  "sex": "string"
}
```

### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|Bm-Token|header|string| 否 ||sessionID|
|body|body|[json$AXuVsjwhOr](#schemajson%24axuvsjwhor)| 否 | json$AXuVsjwhOr|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[Reply](#schemareply)|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|Created|Inline|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|Inline|

### 返回数据结构

<a id="opIdgetCheckCodeUsingGET"></a>

## GET 获取验证码

GET /bm/user/code/{type}

### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|type|path|string| 是 ||短信类型，1为登录模板，2为注册模板，3为修改模板|
|mobile|query|string| 否 ||手机号|
|Bm-Token|header|string| 否 ||sessionID|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[Reply](#schemareply)|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|Inline|

### 返回数据结构

<a id="opIdgetBookByIdUsingGET_1"></a>

## GET 获取用户详细信息

GET /bm/user/user/{id}

### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|id|path|string| 是 ||用户ID|
|Bm-Token|header|string| 否 ||sessionID|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[Reply](#schemareply)|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|Inline|

### 返回数据结构

<a id="opIdloginFromMobileUsingPOST"></a>

## POST 通过手机号登录校验

POST /bm/user/login_mobile

> Body 请求参数

```json
{
  "code": "string",
  "mobile": "string"
}
```

### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|Bm-Token|header|string| 否 ||sessionID|
|body|body|[json$XKQOTwYkBd](#schemajson%24xkqotwykbd)| 否 | json$XKQOTwYkBd|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[Reply](#schemareply)|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|Created|Inline|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|Inline|

### 返回数据结构

# 权限Controller

<a id="opIdgetPermissionByIdUsingGET"></a>

## GET 获取权限详细信息

GET /bm/permission/permission/{id}

### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|id|path|string| 是 ||书籍ID|
|Bm-Token|header|string| 否 ||sessionID|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[Reply](#schemareply)|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|Inline|

### 返回数据结构

<a id="opIddeleteByIdUsingDELETE_1"></a>

## DELETE 通过ID删除权限

DELETE /bm/permission/permission/{id}

### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|id|path|integer| 是 ||id|
|Bm-Token|header|string| 否 ||sessionID|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[Reply](#schemareply)|
|204|[No Content](https://tools.ietf.org/html/rfc7231#section-6.3.5)|No Content|Inline|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|Inline|

### 返回数据结构

<a id="opIdgetPermissionListUsingGET"></a>

## GET 获取权限列表

GET /bm/permission/permission

### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|Bm-Token|header|string| 否 ||sessionID|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[Reply](#schemareply)|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|Inline|

### 返回数据结构

<a id="opIdcreatePermissionUsingPOST"></a>

## POST 新增权限

POST /bm/permission/permission

> Body 请求参数

```json
{
  "code": "string",
  "name": "string",
  "pid": "string"
}
```

### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|Bm-Token|header|string| 否 ||sessionID|
|body|body|[json$tBJKmyciKP](#schemajson%24tbjkmycikp)| 否 | json$tBJKmyciKP|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[Reply](#schemareply)|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|Created|Inline|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|Inline|

### 返回数据结构

# 角色Controller

<a id="opIdgetRoleListUsingGET"></a>

## GET 获取角色列表

GET /bm/role/role

### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|current|query|string| 否 ||当前页，默认为1|
|size|query|string| 否 ||一页展示条数，默认为10|
|Bm-Token|header|string| 否 ||sessionID|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[Reply](#schemareply)|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|Inline|

### 返回数据结构

<a id="opIdcreateRoleUsingPOST"></a>

## POST 新增角色

POST /bm/role/role

> Body 请求参数

```json
{
  "code": "string",
  "name": "string"
}
```

### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|Bm-Token|header|string| 否 ||sessionID|
|body|body|[json$FEFGSQgJJD](#schemajson%24fefgsqgjjd)| 否 | json$FEFGSQgJJD|none|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[Reply](#schemareply)|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|Created|Inline|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|Inline|

### 返回数据结构

<a id="opIdgetRoleByIdUsingGET"></a>

## GET 获取角色详细信息

GET /bm/role/role/{id}

### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|id|path|string| 是 ||角色ID|
|Bm-Token|header|string| 否 ||sessionID|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[Reply](#schemareply)|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|Inline|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Not Found|Inline|

### 返回数据结构

<a id="opIddeleteByIdUsingDELETE_2"></a>

## DELETE 通过ID删除角色

DELETE /bm/role/role/{id}

### 请求参数

|名称|位置|类型|必选|中文名|说明|
|---|---|---|---|---|---|
|id|path|integer| 是 ||id|
|Bm-Token|header|string| 否 ||sessionID|

> 返回示例

### 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|OK|[Reply](#schemareply)|
|204|[No Content](https://tools.ietf.org/html/rfc7231#section-6.3.5)|No Content|Inline|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Unauthorized|Inline|
|403|[Forbidden](https://tools.ietf.org/html/rfc7231#section-6.5.3)|Forbidden|Inline|

### 返回数据结构

# 数据模型

<h2 id="tocS_json$MWQGkQipBI">json$MWQGkQipBI</h2>

<a id="schemajson$mwqgkqipbi"></a>
<a id="schema_json$MWQGkQipBI"></a>
<a id="tocSjson$mwqgkqipbi"></a>
<a id="tocsjson$mwqgkqipbi"></a>

```json
{
  "code": "string",
  "mobile": "string"
}

```

json$MWQGkQipBI

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|code|string|false|none||短信验证码|
|mobile|string|false|none||手机号|

<h2 id="tocS_json$XqJGzhROvZ">json$XqJGzhROvZ</h2>

<a id="schemajson$xqjgzhrovz"></a>
<a id="schema_json$XqJGzhROvZ"></a>
<a id="tocSjson$xqjgzhrovz"></a>
<a id="tocsjson$xqjgzhrovz"></a>

```json
{
  "code": "string",
  "mobile": "string"
}

```

json$XqJGzhROvZ

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|code|string|false|none||短信验证码|
|mobile|string|false|none||手机号|

<h2 id="tocS_json$wGZdatMSau">json$wGZdatMSau</h2>

<a id="schemajson$wgzdatmsau"></a>
<a id="schema_json$wGZdatMSau"></a>
<a id="tocSjson$wgzdatmsau"></a>
<a id="tocsjson$wgzdatmsau"></a>

```json
{
  "password": "string",
  "username": "string"
}

```

json$wGZdatMSau

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|password|string|false|none||登录密码|
|username|string|false|none||用户名或手机号|

<h2 id="tocS_json$AwGtyRIByT">json$AwGtyRIByT</h2>

<a id="schemajson$awgtyribyt"></a>
<a id="schema_json$AwGtyRIByT"></a>
<a id="tocSjson$awgtyribyt"></a>
<a id="tocsjson$awgtyribyt"></a>

```json
{
  "author": "string",
  "book_class": "string",
  "book_number": "string",
  "bookshelf_number": "string",
  "name": "string",
  "over": "string",
  "publishing_house": "string"
}

```

json$AwGtyRIByT

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|author|string|false|none||作者|
|book_class|string|false|none||类别|
|book_number|string|false|none||书籍编码|
|bookshelf_number|string|false|none||书架号|
|name|string|false|none||书名|
|over|string|false|none||库存|
|publishing_house|string|false|none||出版社|

<h2 id="tocS_Reply">Reply</h2>

<a id="schemareply"></a>
<a id="schema_Reply"></a>
<a id="tocSreply"></a>
<a id="tocsreply"></a>

```json
{
  "code": 0,
  "data": {},
  "msg": "string",
  "success": true
}

```

Reply

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|code|integer(int32)|false|none||none|
|data|object|false|none||none|
|msg|string|false|none||none|
|success|boolean|false|none||none|

<h2 id="tocS_loginFromMobileUsingPOSTJson">loginFromMobileUsingPOSTJson</h2>

<a id="schemaloginfrommobileusingpostjson"></a>
<a id="schema_loginFromMobileUsingPOSTJson"></a>
<a id="tocSloginfrommobileusingpostjson"></a>
<a id="tocsloginfrommobileusingpostjson"></a>

```json
"string"

```

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|*anonymous*|string|false|none||none|

<h2 id="tocS_json$pHUBxeGSky">json$pHUBxeGSky</h2>

<a id="schemajson$phubxegsky"></a>
<a id="schema_json$pHUBxeGSky"></a>
<a id="tocSjson$phubxegsky"></a>
<a id="tocsjson$phubxegsky"></a>

```json
{
  "author": "string",
  "book_class": "string",
  "book_number": "string",
  "bookshelf_number": "string",
  "name": "string",
  "over": "string",
  "publishing_house": "string"
}

```

json$pHUBxeGSky

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|author|string|false|none||作者|
|book_class|string|false|none||类别|
|book_number|string|false|none||书籍编码|
|bookshelf_number|string|false|none||书架号|
|name|string|false|none||书名|
|over|string|false|none||库存|
|publishing_house|string|false|none||出版社|

<h2 id="tocS_json$JuIPyAFrTw">json$JuIPyAFrTw</h2>

<a id="schemajson$juipyafrtw"></a>
<a id="schema_json$JuIPyAFrTw"></a>
<a id="tocSjson$juipyafrtw"></a>
<a id="tocsjson$juipyafrtw"></a>

```json
{
  "bookId": "string"
}

```

json$JuIPyAFrTw

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|bookId|string|false|none||书籍ID|

<h2 id="tocS_json$SPSlZapVgB">json$SPSlZapVgB</h2>

<a id="schemajson$spslzapvgb"></a>
<a id="schema_json$SPSlZapVgB"></a>
<a id="tocSjson$spslzapvgb"></a>
<a id="tocsjson$spslzapvgb"></a>

```json
{
  "password": "string",
  "username": "string"
}

```

json$SPSlZapVgB

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|password|string|false|none||登录密码|
|username|string|false|none||用户名或手机号|

<h2 id="tocS_params$UFdccfZdNL">params$UFdccfZdNL</h2>

<a id="schemaparams$ufdccfzdnl"></a>
<a id="schema_params$UFdccfZdNL"></a>
<a id="tocSparams$ufdccfzdnl"></a>
<a id="tocsparams$ufdccfzdnl"></a>

```json
{
  "endTime": "2019-09-19",
  "startTime": "2019-09-11"
}

```

params$UFdccfZdNL

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|endTime|string|false|none||结束时间|
|startTime|string|false|none||开始时间|

<h2 id="tocS_json$FEFGSQgJJD">json$FEFGSQgJJD</h2>

<a id="schemajson$fefgsqgjjd"></a>
<a id="schema_json$FEFGSQgJJD"></a>
<a id="tocSjson$fefgsqgjjd"></a>
<a id="tocsjson$fefgsqgjjd"></a>

```json
{
  "code": "string",
  "name": "string"
}

```

json$FEFGSQgJJD

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|code|string|false|none||角色编码|
|name|string|false|none||角色名|

<h2 id="tocS_json$iYtOoBZfpL">json$iYtOoBZfpL</h2>

<a id="schemajson$iytoobzfpl"></a>
<a id="schema_json$iYtOoBZfpL"></a>
<a id="tocSjson$iytoobzfpl"></a>
<a id="tocsjson$iytoobzfpl"></a>

```json
{
  "bookId": "string",
  "borrowTime": "2022-09-11 00:00:00",
  "returnTime": "2022-09-11 00:00:00"
}

```

json$iYtOoBZfpL

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|bookId|string|false|none||书籍ID|
|borrowTime|string|false|none||借书日期|
|returnTime|string|false|none||还书日期|

<h2 id="tocS_json$JZVeAQoipe">json$JZVeAQoipe</h2>

<a id="schemajson$jzveaqoipe"></a>
<a id="schema_json$JZVeAQoipe"></a>
<a id="tocSjson$jzveaqoipe"></a>
<a id="tocsjson$jzveaqoipe"></a>

```json
{
  "bookId": "string",
  "borrowTime": "2022-09-11",
  "returnTime": "2022-09-11"
}

```

json$JZVeAQoipe

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|bookId|string|false|none||书籍ID|
|borrowTime|string|false|none||借书日期|
|returnTime|string|false|none||还书日期|

<h2 id="tocS_json$NmcsOLlgWf">json$NmcsOLlgWf</h2>

<a id="schemajson$nmcsollgwf"></a>
<a id="schema_json$NmcsOLlgWf"></a>
<a id="tocSjson$nmcsollgwf"></a>
<a id="tocsjson$nmcsollgwf"></a>

```json
{
  "author": "string",
  "book_class": "string",
  "book_number": "string",
  "bookshelf_number": "string",
  "name": "string",
  "publishing_house": "string"
}

```

json$NmcsOLlgWf

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|author|string|false|none||作者|
|book_class|string|false|none||类别|
|book_number|string|false|none||书籍编码|
|bookshelf_number|string|false|none||书架号|
|name|string|false|none||书名|
|publishing_house|string|false|none||出版社|

<h2 id="tocS_json$tBJKmyciKP">json$tBJKmyciKP</h2>

<a id="schemajson$tbjkmycikp"></a>
<a id="schema_json$tBJKmyciKP"></a>
<a id="tocSjson$tbjkmycikp"></a>
<a id="tocsjson$tbjkmycikp"></a>

```json
{
  "code": "string",
  "name": "string",
  "pid": "string"
}

```

json$tBJKmyciKP

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|code|string|false|none||权限编码|
|name|string|false|none||权限名|
|pid|string|false|none||上级ID 源ID为0|

<h2 id="tocS_json$kjyAkzgraj">json$kjyAkzgraj</h2>

<a id="schemajson$kjyakzgraj"></a>
<a id="schema_json$kjyAkzgraj"></a>
<a id="tocSjson$kjyakzgraj"></a>
<a id="tocsjson$kjyakzgraj"></a>

```json
{
  "author": "string",
  "book_class": "string",
  "book_number": "string",
  "bookshelf_number": "string",
  "name": "string",
  "publishing_house": "string"
}

```

json$kjyAkzgraj

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|author|string|false|none||作者|
|book_class|string|false|none||类别|
|book_number|string|false|none||书籍编码|
|bookshelf_number|string|false|none||书架号|
|name|string|false|none||书名|
|publishing_house|string|false|none||出版社|

<h2 id="tocS_json$iAYLTpSySZ">json$iAYLTpSySZ</h2>

<a id="schemajson$iayltpsysz"></a>
<a id="schema_json$iAYLTpSySZ"></a>
<a id="tocSjson$iayltpsysz"></a>
<a id="tocsjson$iayltpsysz"></a>

```json
{
  "author": "string",
  "book_class": "string",
  "book_number": "string",
  "bookshelf_number": "string",
  "name": "string",
  "publishing_house": "string"
}

```

json$iAYLTpSySZ

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|author|string|false|none||作者|
|book_class|string|false|none||类别|
|book_number|string|false|none||书籍编码|
|bookshelf_number|string|false|none||书架号|
|name|string|false|none||书名|
|publishing_house|string|false|none||出版社|

<h2 id="tocS_json$AXuVsjwhOr">json$AXuVsjwhOr</h2>

<a id="schemajson$axuvsjwhor"></a>
<a id="schema_json$AXuVsjwhOr"></a>
<a id="tocSjson$axuvsjwhor"></a>
<a id="tocsjson$axuvsjwhor"></a>

```json
{
  "code": "string",
  "mobile": "string",
  "password": "string",
  "sex": "string"
}

```

json$AXuVsjwhOr

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|code|string|false|none||短信验证码|
|mobile|string|false|none||手机号|
|password|string|false|none||登录密码|
|sex|string|false|none||性别 0=女 1=男|

<h2 id="tocS_json$gWKHgXvKIE">json$gWKHgXvKIE</h2>

<a id="schemajson$gwkhgxvkie"></a>
<a id="schema_json$gWKHgXvKIE"></a>
<a id="tocSjson$gwkhgxvkie"></a>
<a id="tocsjson$gwkhgxvkie"></a>

```json
{
  "password": "string",
  "username": "string"
}

```

json$gWKHgXvKIE

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|password|string|false|none||登录密码|
|username|string|false|none||用户名或手机号|

<h2 id="tocS_json$KHRJDsPQaL">json$KHRJDsPQaL</h2>

<a id="schemajson$khrjdspqal"></a>
<a id="schema_json$KHRJDsPQaL"></a>
<a id="tocSjson$khrjdspqal"></a>
<a id="tocsjson$khrjdspqal"></a>

```json
{
  "bookId": "string"
}

```

json$KHRJDsPQaL

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|bookId|string|false|none||书籍ID|

<h2 id="tocS_json$JFGMhOyofx">json$JFGMhOyofx</h2>

<a id="schemajson$jfgmhoyofx"></a>
<a id="schema_json$JFGMhOyofx"></a>
<a id="tocSjson$jfgmhoyofx"></a>
<a id="tocsjson$jfgmhoyofx"></a>

```json
{
  "code": "string",
  "mobile": "string",
  "password": "string",
  "sex": "string"
}

```

json$JFGMhOyofx

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|code|string|false|none||短信验证码|
|mobile|string|false|none||手机号|
|password|string|false|none||登录密码|
|sex|string|false|none||性别 0=女 1=男|

<h2 id="tocS_json$XKQOTwYkBd">json$XKQOTwYkBd</h2>

<a id="schemajson$xkqotwykbd"></a>
<a id="schema_json$XKQOTwYkBd"></a>
<a id="tocSjson$xkqotwykbd"></a>
<a id="tocsjson$xkqotwykbd"></a>

```json
{
  "code": "string",
  "mobile": "string"
}

```

json$XKQOTwYkBd

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|code|string|false|none||短信验证码|
|mobile|string|false|none||手机号|

<h2 id="tocS_json$qzPrComrTw">json$qzPrComrTw</h2>

<a id="schemajson$qzprcomrtw"></a>
<a id="schema_json$qzPrComrTw"></a>
<a id="tocSjson$qzprcomrtw"></a>
<a id="tocsjson$qzprcomrtw"></a>

```json
{
  "code": "string",
  "mobile": "string",
  "password": "string",
  "sex": "string"
}

```

json$qzPrComrTw

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|code|string|false|none||短信验证码|
|mobile|string|false|none||手机号|
|password|string|false|none||登录密码|
|sex|string|false|none||性别 0=女 1=男|

<h2 id="tocS_json$jgjjeSbUNz">json$jgjjeSbUNz</h2>

<a id="schemajson$jgjjesbunz"></a>
<a id="schema_json$jgjjeSbUNz"></a>
<a id="tocSjson$jgjjesbunz"></a>
<a id="tocsjson$jgjjesbunz"></a>

```json
{
  "author": "string",
  "book_class": "string",
  "book_number": "string",
  "bookshelf_number": "string",
  "name": "string",
  "over": "string",
  "publishing_house": "string"
}

```

json$jgjjeSbUNz

### 属性

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|author|string|false|none||作者|
|book_class|string|false|none||类别|
|book_number|string|false|none||书籍编码|
|bookshelf_number|string|false|none||书架号|
|name|string|false|none||书名|
|over|string|false|none||库存|
|publishing_house|string|false|none||出版社|

