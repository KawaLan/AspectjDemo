# AspectjDemo
使用面向切面处理权限问题及处理点击过快

1.在项目最外层的build.gradle 引入

dependencies {
        classpath 'org.aspectj:aspectjtools:1.8.9'
        classpath 'org.aspectj:aspectjweaver:1.8.9'
}
2.在app里面的build.gradle 添加一下代码

android.applicationVariants.all { variant ->
    JavaCompile javaCompile = variant.javaCompile
    javaCompile.doLast {
        String[] args = ["-showWeaveInfo",
                         "-1.5",
                         "-inpath", javaCompile.destinationDir.toString(),
                         "-aspectpath", javaCompile.classpath.asPath,
                         "-d", javaCompile.destinationDir.toString(),
                         "-classpath", javaCompile.classpath.asPath,
                         "-bootclasspath", android.bootClasspath.join(
                File.pathSeparator)]

        org.aspectj.bridge.MessageHandler handler = new org.aspectj.bridge.MessageHandler(true)
        new org.aspectj.tools.ajc.Main().run(args, handler)
    }
}
3.使用权限模块工具
@Permission(value = {权限}, requestCode = 请求码)
public void xxxx() { //有权限，你的操作 }

@PermissionDenied(value = "您的权限被拒绝了，请先去设置")
private void xxxx(int requestCode) {}

@PermissionCancel(value = "您的权限被取消了，请先去设置")
private void xxxx(int requestCode) {}

如果需要在设置完权限回来处理相应操作的，请在响应页面判断onActivityResult的requestCode返回

主要是注解 Permission ， PermissionDenied ，PermissionCancel
