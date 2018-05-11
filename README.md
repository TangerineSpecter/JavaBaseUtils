# JavaBaseUtils

## 简介
	主要收集一些平时常用的Java开发工具类，内容在不断更新补充中...

### Java基本工具包：
- 工具包地址：https://github.com/TangerineSpecter/JavaBaseUtils

## <a id="Getting_Menu"></a> 目录

- [开始](#Getting_Menu)
- [API](#Geting_Api)
	- [常量基础工具类](#Geting_Constant)
	- [时间工具类](#Geting_Time)
	- [解压工具类](#Geting_Zip)
	- [二维码工具类](#Geting_QRCode)
	- [数字工具类](#Geting_Number)
	- [图片工具类](#Geting_Image)
	- [文件工具类](#Geting_File)
	- [路径处理工具类](#Geting_Path)
	- [Redis工具类](#Geting_Redis)
	- [解密工具类](#Geting_Deciphering)
	- [随机工具类](#Geting_Random)
	- [分词工具类](#Geting_Analyzer)
	- [Excel工具类](#Geting_Excel)

## <a id= "Geting_Api"></a> API

## <a id= "Geting_Constant"></a>常量基础工具类 → [Constant](https://github.com/TangerineSpecter/JavaBaseUtils/blob/master/src/common/util/Constant.java)

## <a id= "Geting_Time"></a>时间工具类 → [TimeUtils](https://github.com/TangerineSpecter/JavaBaseUtils/blob/master/src/common/util/TimeUtils.java)

方法名     | 说明     | 参数     | 返回结果
------|------|-----|-----
timeFormat | 将时间转换成指定格式   |Date(时间) | String(格式化后的时间)
getDate | 将指定的日期字符串转化为日期对象   |String(时间字符串)、String(格式) | Date(日期对象)
timeFormatToDay | 将时间转换成指定格式 yyyy-MM-dd   |Date(时间) | String(格式化后的时间)
timeFormat | 将时间转换成指定格式   |Date(时间)、String(格式) | String(格式化后的时间)
getCurrentTimes | 获取当前时间的时间戳 精确到毫秒   |无 | Long(时间戳)
getDayBeginTimestamp | 获取当天开始的时间戳   | 无 | Long(时间戳)
getDayEndTimestamp | 获取当天结束的时间戳   | 无 | Long(时间戳)
getYesterdayBeginTimestamp | 获取昨天开始的时间戳   | 无 | Long(时间戳)
getSimpleFormat | 获取指定格式的当前时间  | String(格式) | String(格式化后的时间)
getCurrentYear | 获取当前年份  | 无 | String(格式化后的时间)
getTimestramp | 获取特定时间的时间戳  |int(年份)、int(月份)、int(天)、int(小时)、int(分钟)、int(秒) | Long(时间戳)
getDatemill | 将指定格式 转为毫秒  |Date(时间)、String(格式) | Long(时间戳)
getDisparityDay | 获取距离某个日期的天数  |Date(时间)、String(格式) | int(天数)
getWeekDays | 获取某天的星期  |String(时间字符串) | String(星期)
getFinalDay | 获取某年某月最后一天  |int(年份)、int(月份)| int(日)
getStartDay | 获取某年某月第一天  |Date(时间)| Date(时间对象)
getFinalDay | 获取某年某月最后一天  |Date(时间)| Date(时间对象)
judgeLeapYear | 判断某一年是否闰年  |int(年份)| Boolean(判断结果)

## <a id= "Geting_Zip"></a>解压工具类 → [ZipUtils](https://github.com/TangerineSpecter/JavaBaseUtils/blob/master/src/common/util/ZipUtils.java)

方法名     | 说明     | 参数     | 返回结果
------|------|-----|-----
gZip | 压缩数据   |byte\[](要压缩的二进制数据) | byte\[](压缩后的数据)
unGZip | 解压数据   |byte\[](要解压的二进制数据) | byte\[](解压后的数据)
compress | 解压数据   |String(源文件路径)、String(压缩包名字带后缀) | 无

## <a id= "Geting_QRCode"></a>二维码工具类 → [QRCodeUtils](https://github.com/TangerineSpecter/JavaBaseUtils/blob/master/src/common/util/QRCodeUtils.java)

方法名     | 说明     | 参数     | 返回结果
------|------|-----|-----
createQRCode | 生成不带logo的二维码   |String(数据)、String(编码类型)、Map<EncodeHintType, ?>(二维码属性)、int(宽度)、int(高度) | BufferedImage(二维码图片)
createQRCode | 以默认参数生成不带logo的二维码   |String(二维码数据)、int(宽度)、int(高度) | BufferedImage(二维码图片)
createQRCodeWithLogo | 以默认参数生成不带logo的二维码   |String(二维码数据)、Map<EncodeHintType, ?>(二维码属性)、int(宽度)、int(高度)、File(logo文件) | BufferedImage(二维码图片)
createQRCodeWithLogo | 以默认参数生成带logo的二维码   |String(二维码数据)、int(宽度)、int(高度)、File(logo文件) | BufferedImage(二维码图片)

## <a id= "Geting_Number"></a>数字工具类 → [NumberUtils](https://github.com/TangerineSpecter/JavaBaseUtils/blob/master/src/common/util/NumberUtils.java)

方法名     | 说明     | 参数     | 返回结果
------|------|-----|-----
getFullPermutation | 从Array中拿出n个元素进行全排列   |int[](总的数字数组)、int(需要取出进行排列的元素个数) | List<List<Integer>>(所有的排列组合)
listAll | 从m个元素中任取n个并对结果进行全排列   |List<Integer>(用于承载可能的排列情况的List)、int[](总的字符数组，长度为m)、int(从中取得字符个数) | 无

## <a id= "Geting_Image"></a>图片工具类 → [ImageUtils](https://github.com/TangerineSpecter/JavaBaseUtils/blob/master/src/common/util/ImageUtils.java)

方法名     | 说明     | 参数     | 返回结果
------|------|-----|-----
downloadPicture | 传入要下载的图片的url列表，将url所对应的图片下载到本地   |List<String>(url列表) | 无
downloadPicture | 传入要下载的图片的url以及保存地址，将图片下载到本地   |String(图片地址)、String(图片保存路径) | 无
addWaterMark | 给图片加水印   |String(需要处理图片路径)、String(图片保存路径)、int(水印x坐标)、int(水印y坐标)、String(水印内容)、Font(水印字体)、Color(水印颜色) | 无
getWatermarkLength | 获取水印文字总长度   |String(水印的文字)、Graphics2D(Graphics2D类) | 无
getPicData | 获取图片的二进制数据   |String(图片的绝对路径地址) | byte\[](图片二进制数据)
getWebImage | 获取网页所有图片并下载   |String(网页地址)、String(网页编码)、String(存放图片地址) | 无
gethtmlResourceByURL | 根据网站的地址和页面的编码集来获取网页的源代码   |String(网页地址)、String(网页编码) | String(网页源代码)

## <a id= "Geting_File"></a>文件工具类 → [FileUtil](https://github.com/TangerineSpecter/JavaBaseUtils/blob/master/src/common/util/FileUtil.java)

方法名     | 说明     | 参数     | 返回结果
------|------|-----|-----
base64 | 读取文件并压缩数据然后转Base64编码   |String(文件的绝对路径地址) | String(Base64编码)
base64 | 将二进制压缩数据转成Base64编码   |byte\[](要解压的二进制数据) | String(Base64编码)
writeFile | 二进制文件写入文件   |byte\[](二进制数据)、String(文件名)、String(路径地址) | 无
decode | 把经过压缩过的base64串解码解压并写入打磁盘中   |String(压缩过的base64串)、String(文件名)、String(路径地址) | 无
getAllFilePath | 获取路径下的所有文件/文件夹   |String(文件目录地址)、boolean(是否添加子文件夹路径) | List<String>(文件路径集合)
getAllFileName | 获取路径下的所有文件名   |String(文件目录地址)、boolean(是否切割后缀) | List<String>(文件名集合)

## <a id= "Geting_Path"></a>路径处理工具类 → [DirUtils](https://github.com/TangerineSpecter/JavaBaseUtils/blob/master/src/common/util/DirUtils.java)

方法名     | 说明     | 参数     | 返回结果
------|------|-----|-----
getImgDir | 获取系统图片的存放路径   |String(uuid) | String(图片路径)
getAudioDir | 获取系统音频的存放路径   |String(uuid) | String(音频路径)
getVideoDir | 获取系统视频的存放路径   |String(uuid) | String(视频路径)
getDir | 获取系统各类子目录   |String(路径头)、String(uuid名) | String(子目录路径)

## <a id= "Geting_Redis"></a>Redis工具类 → [JedisTool](https://github.com/TangerineSpecter/JavaBaseUtils/blob/master/src/common/util/JedisTool.java)

方法名     | 说明     | 参数     | 返回结果
------|------|-----|-----
getPool | 加密摩斯密码   | 无 | JedisPool(redis连接池对象)
getResource | 获取redis资源  | 无 | Jedis(redis对象)
returnBrokenResource | 释放资源  | Jedis(redis对象)| 无
returnResource | 释放资源  | Jedis(redis对象)| 无
setCache | 设置缓存  | String(key值)、String(value值) | 无
setCache | 设置缓存  | String(key值)、Serializable(序列化结果) | 无
setCache | 设置缓存  | String(key值)、String(value值)、int(秒) | 无
setCache | 设置缓存  | String(key值)、Serializable(序列化结果)、int(秒) | 无
setListCache | 设置缓存多个二进制键值对  | byte\[](二进制数据) | 无
setCache | 缓存二进制数据到关键字  | String(key值)、byte\[](二进制数据) | 无
setnx | 判断式设置缓存  | String(key值)、String(value值) | int(判断结果返回值)
setnx | 判断式设置缓存  | String(key值)、Serializable(序列化结果) | int(判断结果返回值)
setnx | 判断式设置缓存  | String(key值)、byte\[](二进制数据) | int(判断结果返回值)
setExpire | 设置过期时间  | String(key值)、int(秒) | int(判断结果返回值)
getset | 缓存字符串到关键字key 返回key上一次存储的字符串  | String(key值)、String(value值) | String(上一次缓存value值)
getValCache | 获取缓存  | String(key值) | String(value值)
getObjCache | 获取缓存  | String(key值) | Object(value值)
getDataCache | 根据关键字获取二进制数据缓存  | String(key值) | byte\[](二进制value值)
delCache | 删除缓存数据  | String(key值) | 无

## <a id= "Geting_Deciphering"></a>解密工具类 → [DecipheringUtils](https://github.com/TangerineSpecter/JavaBaseUtils/blob/master/src/common/util/DecipheringUtils.java)

方法名     | 说明     | 参数     | 返回结果
------|------|-----|-----
setMorseResult | 加密摩斯密码   |String(加密内容) | String(加密结果)
getMorseResult | 解密摩斯密码  |String(解密内容) | String(解密结果)
setRailFenceResult | 栅栏密码加密   |String(栅栏密码)、int(栅栏数) | String(加密结果)
getRailFenceResult | 栅栏密码解密   |String(栅栏密码)、int(栅栏数) | String(解密结果)
string2Unicode | 字符串转unicode   |String(字符串) | String(unicode)
unicode2String | unicode转字符串   |String(unicode) | String(字符串)
setPhoneTypewritingResult | 手机九宫格输入法加密   |String(加密内容) | String(加密结果)
getPhoneTypewritingResult | 手机九宫格输入法解密  |String(解密内容) | String(解密结果)
setKeyboardResult | 加密键盘密码   |String(加密内容) | String(加密结果)
getKeyboardResult | 解密键盘密码  |String(解密内容) | String(解密结果)
setBaconResult | 加密培根密码   |String(加密内容) | String(加密结果)
getBaconResult | 解密培根密码  |String(解密内容) | String(解密结果)
reverseOrder | 倒序排列  |String(字符串内容) | String(倒序结果)

## <a id= "Geting_Random"></a>随机工具类 → [RandomUtils](https://github.com/TangerineSpecter/JavaBaseUtils/blob/master/src/common/util/RandomUtils.java)

方法名     | 说明     | 参数     | 返回结果
------|------|-----|-----
getNum | 随机数   |int(起始数)、int(结束数) | int(随机数)
getTel | 随机生成电话号码   | 无 | String(电话号码)
getChineseName | 随机生成中文名字   | 无 | String(中文名)
getEmail | 随机生成Email   | int(最小长度)、int(最大长度) | String(Email)
getDate | 随机生成时间   | 无 | String(时间字符串)
getProvince | 随机生成省份   | 无 | String(省份)

## <a id= "Geting_Analyzer"></a>分词工具类 → [IKTokenizerTool](https://github.com/TangerineSpecter/JavaBaseUtils/blob/master/src/common/util/IKTokenizerTool.java)

方法名     | 说明     | 参数     | 返回结果
------|------|-----|-----
tokenizeKeyWord | 切分分词   |String(分词内容)、boolean(是否开启智能切分) | String(分词结果)
tokenizeKeyWord | 切分分词   |String(分词内容)、boolean(是否开启智能切分) | List<String>(分词结果)

## <a id= "Geting_Excel"></a>Excel工具类 → [ExcelUtils](https://github.com/TangerineSpecter/JavaBaseUtils/blob/master/src/common/util/ExcelUtils.java)
getExcelData
方法名     | 说明     | 参数     | 返回结果
------|------|-----|-----
createExcel | 创建Excel文件   |String\[](表头), List<String\[]>(数据列表), boolean(生成格式：true:xlsx格式；false:xls格式) | String(excel生成路径)
getExcel | 获取Excel数据   |String(文件路径)|  List<String\[]>(数据列表)