--使用luajava绑定一个java类
local logger = luajava.bindClass("com.liotngjava.luaj.demo01.TestLogger");
--调用类的静态方法/变量
logger:info("test call static java function in lua")
print(logger.TAG)
-- 使用绑定类创建类的实例（对象）
local logger_instance = luajava.new(logger)
-- 调用对象方法
logger_instance:testLogger("Test call java in lua1")