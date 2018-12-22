# JavaBaseUtils

## 简介
	主要收集一些平时常用的Java开发工具类，内容在不断更新补充中...
<img src="src/common/img/show_logo.gif">

### Java基本工具包：
- 工具包地址：https://github.com/TangerineSpecter/JavaBaseUtils

### 版本号：
- 项目版本：1.1.5

- JDK版本：1.7

### 最后更新时间：
> 2018-12-23

## <a id="Getting_Menu"></a> 目录 

- [开始](#Getting_Menu)
- [API](#Geting_Api)
    - [路径处理工具类](#Geting_DirUtils)
    - [加密工具类](#Geting_EncrypUtils)
    - [Excel处理工具类](#Geting_ExcelUtils)
    - [文件工具类](#Geting_FileUtil)
    - [Http工具类](#Geting_HttpUtils)
    - [分词工具类](#Geting_IKTokenizerTool)
    - [图片处理工具类](#Geting_ImageUtils)
    - [数字处理工具类](#Geting_NumberUtils)
    - [PDF工具类](#Geting_PDFUtils)
    - [二维码生成工具类](#Geting_QrCodeUtils)
    - [随机工具类](#Geting_RandomUtils)
    - [正则表达式工具类](#Geting_RegExUtils)
    - [字符串处理工具类](#Geting_StringUtils)
    - [时间处理工具类](#Geting_TimeUtils)
    - [压缩和解压工具类](#Geting_ZipUtils)
---
## <a id= "Geting_DirUtils"></a>路径处理工具类 -> [DirUtils](https://github.com/TangerineSpecter/JavaBaseUtils/blob/master/src/common/util/DirUtils.java)
方法名     | 说明     | 参数     | 返回结果
------|------|-----|-----
getImgDir | 获取系统图片的存放路径 | String(UUID) | String(图片路径)
getAudioDir | 获取系统音频的存放路径 | String(UUID) | String(音频路径)
getVideoDir | 获取系统视频的存放路径 | String(UUID) | String(视频路径)
---
## <a id= "Geting_EncrypUtils"></a>加密工具类 -> [EncrypUtils](https://github.com/TangerineSpecter/JavaBaseUtils/blob/master/src/common/util/EncrypUtils.java)
方法名     | 说明     | 参数     | 返回结果
------|------|-----|-----
hash | 哈希加密算法 | byte\[](加密字节数组),String(加密算法名称) | String(加密数据)
hash | 哈希加密算法 | String(需要加密的数据),String(加密算法名称) | String(加密数据)
encodeHex | 将字节数组转换成十六进制字符串 | byte\[](字节数组) | String(十六进制字符串)
---
## <a id= "Geting_ExcelUtils"></a>Excel处理工具类 -> [ExcelUtils](https://github.com/TangerineSpecter/JavaBaseUtils/blob/master/src/common/util/ExcelUtils.java)
方法名     | 说明     | 参数     | 返回结果
------|------|-----|-----
createExcel | 创建Excel | String\[](表头),List(数据列表),boolean(新旧版本) | String(生成路径)
getExcel | 获取Excel数据 | String(Excel路径) | List(数据列表)
---
## <a id= "Geting_FileUtil"></a>文件工具类 -> [FileUtil](https://github.com/TangerineSpecter/JavaBaseUtils/blob/master/src/common/util/FileUtil.java)
方法名     | 说明     | 参数     | 返回结果
------|------|-----|-----
decode | 把压缩过的base64串解码解压写入磁盘中 | String(压缩过的base64串),String(文件名),String(路径地址) | void(无)
getAllFilePath | 获取路径下的所有文件/文件夹 | String(需要遍历的文件夹路径),boolean(是否将子文件夹的路径也添加到list集合中) | List(文件路径集合)
getAllFileName | 获取路径下的所有文件名 | String(需要遍历的文件夹路径),boolean(是否切割后缀) | List(文件名集合)
moveFuzzyFileDir | 转移文件目录（包含名字） | String(文件名),String(旧路径),String(新路径),boolean(是否覆盖) | void(无)
createFile | 创建文件 | String(生成路径),String(文件名),List(文本内容),FileTypeEnum(文件类型) | void(无)
createFile | 创建文件 | String(生成路径),List(文本内容),FileTypeEnum(文件类型) | void(无)
createFile | 创建文件 | List(文本内容),FileTypeEnum(文件类型) | void(无)
deleteFile | 删除文件 | String(文件路径),String(文件名) | void(无)
writeFile | 二进制文件写入文件 | byte\[](二进制数据),String(文件名),String(路径地址) | void(无)
base64 | 读取文件并压缩数据然后转Base64编码 | String(文件的绝对路径地址) | String(转码结果)
base64 | 将二进制压缩数据转成Base64编码 | byte\[](二进制压缩数据) | String(base64编码)
moveFileDir | 转移文件目录 | String(文件名),String(旧路径),String(新路径),boolean(是否覆盖) | void(无)
deleteDirFile | 删除文件夹 | String(文件夹路径),boolean(是否删除文件夹内容) | void(无)
---
## <a id= "Geting_HttpUtils"></a>Http工具类 -> [HttpUtils](https://github.com/TangerineSpecter/JavaBaseUtils/blob/master/src/common/util/HttpUtils.java)
方法名     | 说明     | 参数     | 返回结果
------|------|-----|-----
sendPost | post请求 | String(请求地址),Map(请求参数),Map(请求头) | String(请求结果)
---
## <a id= "Geting_IKTokenizerTool"></a>分词工具类 -> [IKTokenizerTool](https://github.com/TangerineSpecter/JavaBaseUtils/blob/master/src/common/util/IKTokenizerTool.java)
方法名     | 说明     | 参数     | 返回结果
------|------|-----|-----
tokenizeKeyWord | 切分分词 | String(关键词),boolean(智能切分) | String(分词结果)
tokenizeKeyWordList | 切分分词 | String(关键词),boolean(智能切分) | List(分词结果)
---
## <a id= "Geting_ImageUtils"></a>图片处理工具类 -> [ImageUtils](https://github.com/TangerineSpecter/JavaBaseUtils/blob/master/src/common/util/ImageUtils.java)
方法名     | 说明     | 参数     | 返回结果
------|------|-----|-----
downloadPicture | 将Url图片下载到本地 | String(url地址),String(保存路径) | void(无)
downloadPicture | 将Url图片下载到本地 | List(url列表) | void(无)
getWatermarkLength | 获取水印文字总长度 | String(水印文字),Graphics2D(Graphics2D类) | int(水印文字总长度)
gethtmlResourceByURL | 获取网页源代码 | String(网页地址),String(编码集) | String(源代码)
getPicData | 获取图片的二进制数据 | String(图片的绝对路径地址) | byte[](二进制数据)
base64 | 读取文件压缩后转Base64编码 | String(图片的绝对路径地址) | String(Base64编码)
getWebImage | 获取网页所有图片并下载 | String(网页地址),String(网页编码),String(存放路径) | void(无)
addWaterMark | 给图片加水印 | String(需要处理的图片路径),String(图片保存路径),int(水印x坐标),int(水印y坐标),String(水印内容),Font(水印字体),Color(水印字体颜色) | void(无)
---
## <a id= "Geting_NumberUtils"></a>数字处理工具类 -> [NumberUtils](https://github.com/TangerineSpecter/JavaBaseUtils/blob/master/src/common/util/NumberUtils.java)
方法名     | 说明     | 参数     | 返回结果
------|------|-----|-----
getFullPermutation | 从Array中拿出n个元素进行全排列 | char\[](字符数组),int(取出的元素个数) | void(无)
getFullPermutation | 从Array中拿出n个元素进行全排列 | int\[](数字数组),int(需要取出的元素个数) | List(排列结果)
listAll | 从m个元素中任取n个并对结果进行全排列 | List(装载排列结果list),char\[](字符数组),int(取出的元素个数) | void(无)
listAll | 从m个元素中任取n个并对结果进行全排列 | List(装载排列结果list),int\[](数字数组),int(取出的元素个数) | void(无)
---
## <a id= "Geting_PDFUtils"></a>PDF工具类 -> [PDFUtils](https://github.com/TangerineSpecter/JavaBaseUtils/blob/master/src/common/util/PDFUtils.java)
方法名     | 说明     | 参数     | 返回结果
------|------|-----|-----
createPdf | 创建PDF | 无 | void(无)
createPdf | 创建PDF | String(生成路径),List(文本内容) | void(无)
---
## <a id= "Geting_QrCodeUtils"></a>二维码生成工具类 -> [QrCodeUtils](https://github.com/TangerineSpecter/JavaBaseUtils/blob/master/src/common/util/QrCodeUtils.java)
方法名     | 说明     | 参数     | 返回结果
------|------|-----|-----
createQRCodeWithLogo | 生成带logo的默认参数二维码 | String(数据),int(宽度),int(高度),File(logo文件路径) | BufferedImage(二维码图片)
createQRCodeWithLogo | 生成带logo的二维码 | String(数据),String(编码类型),Map(二维码属性),int(宽度),int(高度),File(logo文件路径) | BufferedImage(二维码图片)
createQRCode | 生成不带logo的二维码 | String(数据),String(编码类型),Map(二维码属性),int(宽度),int(高度) | BufferedImage(二维码图片)
createQRCode | 生成不带logo的默认参数二维码 | String(数据),int(编码类型),int(宽度) | BufferedImage(二维码图片)
---
## <a id= "Geting_RandomUtils"></a>随机工具类 -> [RandomUtils](https://github.com/TangerineSpecter/JavaBaseUtils/blob/master/src/common/util/RandomUtils.java)
方法名     | 说明     | 参数     | 返回结果
------|------|-----|-----
getDate | 随机生成时间 | 无 | String(时间)
createRandomName | 创建随机字符名字 | long(名字长度) | String(随机结果)
getChineseName | 随机生成中文名字 | 无 | String(中文名)
getTel | 随机生成电话号码 | 无 | String(电话号码)
getEmail | 随机生成Email | int(最小长度),int(最大长度) | String(Email)
getNum | 随机数 | int(起始数),int(结束数) | int(随机数字)
getPROVINCE | 随机生成省份 | 无 | String(省份)
---
## <a id= "Geting_RegExUtils"></a>正则表达式工具类 -> [RegExUtils](https://github.com/TangerineSpecter/JavaBaseUtils/blob/master/src/common/util/RegExUtils.java)
方法名     | 说明     | 参数     | 返回结果
------|------|-----|-----
removeSpecialCharacter | 移除特殊字符 | String(字符串内容) | String(处理结果)
checkEmail | 校验邮箱合法化 | String(邮箱地址) | boolean(校验结果)
check2Point | 校验数字为小数后两位以内 | String(校验数字) | boolean(校验结果)
checkPassword | 校验密码以字母开头 | String(密码) | boolean(校验结果)
filterHtml | 去除富文本中html相关字符 | String(富文本内容) | String(处理结果)
---
## <a id= "Geting_StringUtils"></a>字符串处理工具类 -> [StringUtils](https://github.com/TangerineSpecter/JavaBaseUtils/blob/master/src/common/util/StringUtils.java)
方法名     | 说明     | 参数     | 返回结果
------|------|-----|-----
isEmpty | 判断字符串是否为空 | String(字符串内容) | boolean(判断结果)
getLocalhostIP | 获取本机IP地址 | 无 | String(IP地址)
subString | 截取字符串开头指定长度 | String(字符串内容),int(截取位置) | String(截取结果)
isNumber | 判断是否为数字 | 无 | boolean(判断结果)
isAllNumber | 判断所有字符串是否都为数字 | String\[](字符串集) | boolean(判断结果)
getOrderNum | 订单号生成 | 无 | String(订单号)
isAnyEmpty | 判断多个字符串中是否有空值 | String\[](字符串参数集) | boolean(判断结果)
randomString | 伪随机字符串 | int(字符串长度) | String(随机结果)
---
## <a id= "Geting_TimeUtils"></a>时间处理工具类 -> [TimeUtils](https://github.com/TangerineSpecter/JavaBaseUtils/blob/master/src/common/util/TimeUtils.java)
方法名     | 说明     | 参数     | 返回结果
------|------|-----|-----
getDate | 将指定的日期字符串转化为日期对象 | String(日期字符串),String(日期格式) | Date(转换结果)
getCurrentYear | 获取当前年份 | 无 | String(年份)
getDisparityDay | 获取距离某个日期的天数 | String(时间字符串) | Integer(天数)
getDayEndTimestamp | 获取当天结束时间戳 | 无 | Long(时间戳)
getCurrentTimes | 获取当前时间戳 | 无 | Long(时间戳)
getYesterdayBeginTimestamp | 获取昨天开始时间戳 | 无 | Long(时间戳)
getDayBeginTimestamp | 获取当天开始时间戳 | 无 | Long(时间戳)
timeFormatToDay | 将时间格式精确到天 | Date(时间) | String(转换结果)
getSimpleFormat | 获取指定格式当前时间 | String(时间格式) | String(时间字符串)
getTimestramp | 获取特定时间时间戳 | int(年份),int(月份),int(日期),int(小时),int(分钟),int(秒) | Long(时间戳)
getWEEKDAYS | 获取某天的星期 | String(时间字符串) | String(星期)
getFinalDay | 获取某年某月最后一题 | int(年份),int(月份) | Integer(天数)
getFinalDay | 获取某年某月第一天 | Date(时间) | Date(时间)
judgeLeapYear | 判断某一年是否闰年 | int(年份) | Boolean(判断结果)
getStartDay | 获取某年某月第一天 | Date(时间) | Date(时间)
timeFormat | 将时间转换成指定格式 | Date(时间),String(时间格式) | String(转换结果)
timeFormat | 将时间转换成指定格式 | Date(时间) | String(转换结果)
getDatemill | 将指定格式转换成毫秒 | String(时间字符串),String(时间格式) | Long(时间戳)
---
## <a id= "Geting_ZipUtils"></a>压缩和解压工具类 -> [ZipUtils](https://github.com/TangerineSpecter/JavaBaseUtils/blob/master/src/common/util/ZipUtils.java)
方法名     | 说明     | 参数     | 返回结果
------|------|-----|-----
gZip | 压缩数据 | byte\[](二进制数据) | byte[](压缩结果)
unGZip | 解压数据 | byte\[](二进制数据) | byte[](解压结果)
compress | 压缩文件 | String(源文件路径),String(压缩包名字) | void(无)
---
