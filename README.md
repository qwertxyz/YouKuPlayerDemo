# YouKuPlayerDemo
优酷视频播放器SDK，android stuido项目结构
# Android优酷播放器SDK——Eclipse工程迁移Android Studio遇到问题

希望大家看完本文章能解决遇到的问题，如果没有表明清楚问题所在，还望指点一二；

- **下载优酷视频播放器SDK**
- **导入Eclipse工作空间**
- **打开Android Studio，新建Project-->YouKuPlayerDemo**
- **新建Moudle-->YouKuPlayerSDK**
- **开始代码的迁移**

-------------------

##  	下载优酷视频播放器SDK

 - android sdk精简版[下载地址](http://open.youku.com/down#on_android) (你也可以下载完整版，看自己需求。)
 - ![SDK截图](http://img.blog.csdn.net/20150410191938552)


----------


## 导入Eclipse工作空间

> 为了方便查看包名、代码

 - Eclipse 优酷SDK项目结构
 

 - ![优酷SDK项目结构](http://img.blog.csdn.net/20150410192942070)

 - Eclipse 优酷PlayerDemo项目结构
 - ![PlayerDemo项目结构](http://img.blog.csdn.net/20150410192807241)

----------


### 新建Project-->YouKuPlayerDemo

 - 将代码复制到app目录下面
 
 - ![列表内容](http://img.blog.csdn.net/20150410193052227)


----------


###新建Moudle-->YouKuPlayerSDK

 - 新建Moudle
 
 - ![列表内容](http://img.blog.csdn.net/20150410193245280)


----------

 - 包名要和优酷SDK的一样，减少不必要的工作

![这里写图片描述](http://img.blog.csdn.net/20150410193907961)

----------

### 开始代码的迁移


 - 打开Eclipse的工作空间，定位到E:\YoukuPlayerOpenSDK\src\com\youku目录下，复制全部文件夹
 
 ![这里写图片描述](http://img.blog.csdn.net/20150410194456372)


----------

 - 打开Android Studio工作空间，定位到
 E:\android_workspace_studio\YouKuPlayerDemo\youkuplayersdk\src\main\java\com\youku目录下，将文件夹全部复制过来

![这里写图片描述](http://img.blog.csdn.net/20150410194655498)


----------


 - 用同样的办法，把res、libs目录下文件也复制过来。
 - res文件夹

![res](http://img.blog.csdn.net/20150410195121022)

----------

 - libs文件夹

![libs](http://img.blog.csdn.net/20150410195015299)


----------


> android studio AIDL文件与java的关联和Eclipse是不一样的，studio需要手动在main\下新建aidl文件夹，将.aidl文件复制过来。

 - 关于aidl的引用
 - [http://www.linuxidc.com/Linux/2015-01/111148.htm](http://www.linuxidc.com/Linux/2015-01/111148.htm)
 - [http://stackoverflow.com/questions/17836234/how-can-i-add-the-aidl-file-to-android-studio-from-the-in-app-billing-example](http://stackoverflow.com/questions/17836234/how-can-i-add-the-aidl-file-to-android-studio-from-the-in-app-billing-example)


----------

## .so文件的配置 ##

需要在工程的build.gradle设置sourceSets

![这里写图片描述](http://img.blog.csdn.net/20150410195704632)


----------


## 最后将YouKuPlayerSDK关联到app工程下 ##

![这里写图片描述](http://img.blog.csdn.net/20150410195852054)

> bulid工程出现错误，检查jar是否冲突。

 - 提示.9图片错误，解决办法[http://segmentfault.com/q/1010000002634455](http://segmentfault.com/q/1010000002634455)


----------

 - Android Studio项目结构[下载链接](http://download.csdn.net/detail/elsdnwn/8583687)
 - GitHub [链接地址](https://github.com/elsdnwn/YouKuPlayerDemo)
 
