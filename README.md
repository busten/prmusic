# 纯白音乐(prmusic.xyz)

## 输入 https://prmusic.xyz/admin_home 可以进入管理页面，管理页面还未完成，只有大概模板。

## 所有数据交互都经过加密处理，只要不是针对性的受到攻击，相信安全方面是没什么问题的。

## PC建议使用谷歌浏览器，浏览，目前没对其它浏览器做兼容，安卓随意，只要上得了牌面的浏览器都行(经测试安卓原生的浏览器也没问题)。目前发现小米浏览器存在一些问题。

## 前端代码属于初期，没有什么优化，目标就是能用就行。后端响应可能偶尔会比较慢，因为服务器比较垃圾，就一个1核2g的vps，装的软件比较多，所有相关访问都靠这一颗核心处理，所以导致在性能上会出现不足。

## 经测试发现邮箱服务貌似早期就屏蔽了服务器的ip，在后台部署的服务器上无法连接到邮箱服务地址，导致通过域名访问的邮箱注册服务不能使用，目前在本地运行可以正常使用，因为手上服务器不多，所以无法完全确定是不是部署的服务器ip被ban了

## 目前还有个问题就是使用谷歌浏览器登陆跳转页面时，会大概率弹出警告，提示数据泄露，目前还不知道这是什么原因造成的，至少代码方面没发现什么问题，而且此问题也只是在输入类似密码框跳转页面的时候才会出现。

# 待解决问题:
### 1、移动端页面的兼容问题(主要是没有设计的灵感，很多地方不知道该怎么排美观)
### 2、前端页面代码运行效率
### 3、代码优化
### 4、谷歌浏览器数据泄露的提示(一头雾水，目前感觉是页面销毁方面导致内存溢出的问题)
### 5、其它小问题

* PS:之前曾经做过一个音乐网站，但是后面做出来实在是感觉太丑了，因此这个网站也算是第二版，主要是太没有设计方面的功底了...
* 牵涉的技术:
  * 后端:pring、SpringMvc、Mybatis、Redis、Minio、Jwt。
  * 前端:vuecli3(Vue全家桶)、vant、echarts、axios。
* 目前网页上的版本算是测试版，等搜索功能完善后将会上传到服务器。
