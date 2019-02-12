1.在项目最外层的build.gradle 引入  
```
dependencies {
        classpath 'org.aspectj:aspectjtools:1.8.9'
        classpath 'org.aspectj:aspectjweaver:1.8.9'
}
```
2.在app里面的build.gradle 添加一下代码  
```
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
```

3.使用方法

+ 默认提示的

    /**
     * 去拍照
     */
    @Permission(value = {Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE},
            requestCode = 1)
    private void goTakePicture() {

    }
注意：如果一个页面有多处方法需要处理权限的，并且是需要处理返回码requestCode的，requestCode的一定要从1，2，3....的次序

+ 需要自定义提示
  @PermissionDenied(value = "你的权限被拒绝了")
    private void PermissionDenied(int requestCode) {
  }

  @PermissionCancel(value = "您的权限被取消了，请先去设置")
    private void PermissionCancel(int requestCode) {
  }

+ 自定义弹框
    @PermissionDenied(isDefaultDialog = true)
    private void PermissionDenied(int requestCode) {
    }

    @PermissionCancel(isDefaultDialog = true)
    private void PermissionCancel(int requestCode) {
    }

如果需要在设置完权限回来处理相应操作的，请在响应页面判断onActivityResult的requestCode返回

主要是注解 Permission ， PermissionDenied ，PermissionCancel



