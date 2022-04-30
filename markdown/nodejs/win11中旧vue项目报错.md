## win11中旧vue项目报错问题解决

针对windows11系统中旧的vue工程安装依赖时，发现报错：

```shell
RunScriptError: Run "C:\Windows\system32\cmd.exe /d /s /c node rebuild"
```

原因：这个报错是安装了windows11以后，[node](https://so.csdn.net/so/search?q=node&spm=1001.2101.3001.7020)-gyp未配置安装python2.7以及VC++ build Tools依赖（这个应该是win11系统未进行安装，因为我之前的win10系统都有安装）

方案：
第一步：打开命令面板(win+R,输入[cmd](https://so.csdn.net/so/search?q=cmd&spm=1001.2101.3001.7020))

```shell
// 安装node-gyp
cnpm install -g node-gyp
//使用npm安装Windows的C ++构建工具-源码
cnpm install --global --production windows-build-tools
```

第二步：右键`我的电脑`—点击`属性`—找到`高级系统设置`—找到`高级`—找到`环境变量`—选择系统变量下的`path`—新建，将 `C:\Users\Administrator\.windows-build-tools\python27` 输入即可；

第三步：此时便可以重新安装vue工程的依赖，如若还报错，再次打开命令面板，然后输入` node-gyp list `；

```shell
node-gyp list
```

第四步：之后再安装依赖即可

