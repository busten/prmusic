# 纯白音乐(prmusic.xyz)

## 目前网站的状态介绍;
  * 所有数据交互都经过加密处理，只要不是针对性的受到攻击，相信安全方面是没什么问题的。(可以使用浏览器调试工具查看)
  * 播放器可以正常使用，移动端目前除了在小米浏览器发现的问题外其它暂未发现什么问题。
  * PC建议使用谷歌浏览器，浏览，目前没对其它浏览器做兼容，安卓随意，只要上得了牌面的浏览器都行(经测试安卓原生的浏览器也没问题)。唯一问题也就是其它浏览器的排版和滚动条隐藏的问题。
  * 后台对于前端获取数据可以有很多种方式，但是获取加密数据只能使用HttpServletrequest的封装类，不然会抛解码错误的异常。
  * 后台也可以简单的更改与前端的交互方式，可以随时改成非json交互。
  * 后台目前还没启用csrf防御，还没有重写csrf的控制器，现阶段开启的话会造成csrf身份验证识别不了的问题。跨域也没有限制，任何人都可以访问几个公共api(首先你要查到api接口地址是什么)

## 目前网站待定事项:
  * 输入 https://prmusic.xyz/admin_home 可以进入管理页面，管理页面还未完成，只有大概模板。
  * 主页的用户信息暂不更新，因为测试需要，等完善了才会与后台进行通信.
  * 页面的个人列表和收藏合辑暂时不会进行完善，这是后面才考虑到的自定义列表播放和合辑收藏。
  * 歌曲展示面板的歌曲时间待定，可能需要把后台管理页面做出了才能录入时间信息。

## 前端代码属于初期，没有什么优化，目标就是能用就行。后端响应可能偶尔会比较慢，因为服务器比较垃圾，就一个1核2g的vps，装的软件比较多，所有相关访问都靠这一颗核心处理，所以导致在性能上会出现不足。

## 经测试发现邮箱服务貌似早期就屏蔽了服务器的ip，在后台部署的服务器上无法连接到邮箱服务地址，导致通过域名访问的邮箱注册服务不能使用，目前在本地运行可以正常使用，因为手上服务器不多，所以无法完全确定是不是部署的服务器ip被ban了

## 目前还有个问题就是使用谷歌浏览器登陆跳转页面时，会大概率弹出警告，提示数据泄露，目前还不知道这是什么原因造成的，至少代码方面没发现什么问题，而且此问题也只是在输入类似密码框跳转页面的时候才会出现。

# 待解决问题:
### 1、移动端页面的兼容问题(主要是没有设计的灵感，很多地方不知道该怎么排美观)
### 2、前端页面代码运行效率以及用户交互优化
### 3、代码优化
### 4、谷歌浏览器数据泄露的提示(一头雾水，目前感觉是页面销毁方面导致内存溢出的问题)
### 5、其它小问题

* PS:之前曾经做过一个音乐网站，但是后面做出来实在是感觉太丑了，因此这个网站也算是第二版，主要是太没有设计方面的功底了...
* 牵涉的技术:
  * 后端:pring、SpringMvc、Mybatis、Redis、Minio、Jwt。
  * 前端:vuecli3(Vue全家桶)、vant、echarts、axios。
* 目前网页上的版本算是测试版，等大部分功能完善后才会更新代码。
前后花费时间约一个月，时间主要耗在了网页设计和数据库设计方面，其它的都还是算快的，毕竟很多工具都是我早就封装好的，直接拿来用就是了。

