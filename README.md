# CartCourse    

登陆页面、注册页面和商品显示页面具有较好的响应式效果

## 1.登陆功能  

[登陆页面http://47.106.199.67:8080/CartCourse/](http://47.106.199.67:8080/CartCourse/)    

用户名：1785179322@qq.com  1785179333@qq.com  2785179322@qq.com            密码都是：123366  

验证码只有abcd四个字母和“+”号的排列组合，登陆不成功会有相应的弹窗提示

## 2.注册功能  

注册可以直接点击登陆页面的“立即注册”进入注册页面，或点击链接进入。注册功能没有识别用户名的格式。

[登陆页面http://47.106.199.67:8080/CartCourse/register.html](http://47.106.199.67:8080/CartCourse/register.html)  

为了限制未登陆用户访问，除css、js、properties、jpg、png、jpeg等文件外，只有登陆页面、注册页面、获取验证码的servlet可以  

直接访问，直接访问其它界面都会跳转到登陆界面  

## 3.session实现简易购物车  

成功登陆后，用户名会显示在左上角，同时会统计当前在线的人数。点击商品图片或名字会进入商品信息详情展示页面，在该页面中可以选择  

购买该商品的数量，将商品加入购物车，查看购物车信息；同时在该页面中还会有最近几条浏览记录的显示，点击它们也可以进入对应商品的  

详情展示页面  

## 4.文件上传功能
文件上传功能同样需要在登陆后才能使用，该页面会被拦截器拦截，但是进入之后有没有设置进入该页面的连接。  

所以只有登陆后，直接在浏览器中输入地址才能进行文件上传

[文件上传界面http://47.106.199.67:8080/CartCourse/upload.jsp](http://47.106.199.67:8080/CartCourse/upload.jsp)    

文件成功上传后，可以在tomcat服务器的对应项目CartCourse下的WEB-INF文件夹下看到上传的文件

