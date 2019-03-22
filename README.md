## java 动态代理

动态代理在运行时生成代理类的字节码，从字节码中创建出代理类的实例，对其所有的方法调用都转发到 invocation handler 的 invoke 方法，在 invoke 方法中执行额外的逻辑。

将动态生成的字节码保存到文件中
```
    // 获取 class 文件字节码，可以将该字节码保存到文件
    byte[] proxyClassFile = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{Interface.class});
    saveToFile(proxyClassFile);
    
    private static void saveToFile(byte[] proxyClassFile) {
        File file = new File("./proxy.class");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(proxyClassFile);
            fos.flush();
            fos.close();
            System.out.println("saved...");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
```

## AOP













