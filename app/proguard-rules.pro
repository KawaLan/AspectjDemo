# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
#类中所有成员不混淆
-keep @com.kawa.aspectjlib.annotation.obfuscator.ObfuscateKeepAll class * { *; }

#类中所有public成员不混淆
-keep @com.kawa.aspectjlib.annotation.obfuscator.ObfuscateKeepPublic class * {
  public <fields>;
  public <methods>;
}

#保留带注释的成员,适用于类和内部类
-keepclassmembers class * {
@com.kawa.aspectjlib.annotation.obfuscator.ObfuscateKeepThisField * ;
}

#保留类中set*/get*/is*函数
-keepclassmembers @com.kawa.aspectjlib.annotation.obfuscator.ObfuscateKeepSetterGetter class * {
    void set*(***);
    boolean is*();
    *** get*();
}
#保留所有实现IObfuscateKeepAll接口的类,(注意接口有传递性,他的子类也会被keep)(内部类如果没有继承此接口会被混淆)
-keep class * implements com.kawa.aspectjlib.annotation.obfuscator.IObfuscateKeepAll {
	*;
}