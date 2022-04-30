## nodejs与npm版本过高问题解决

本地nodejs和npm版本过高，无法正常使用npm下载低npm版本项目所需依赖，不降级解决办法。这里需要使用到cnpm：

1.导入下载好到的项目

2.安装cnpm： 

```shell
npm install cnpm -g
```

3.安装 node-sass：

```shell
cnpm install node-sass
```

4.继续安装：

```shell
cnpm i node-sass -D
```

5.删除项目中出现的 node_modules文件夹

6.根据package.json重新安装依赖： 

```shell
cnpm install
```

7.在开发环境中启动项目：

```shell
npm run dev
```

