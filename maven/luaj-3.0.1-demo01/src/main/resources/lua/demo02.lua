--使用luajava创建java类的实例（对象）
local logger = luajava.newInstance("com.liotngjava.luaj.demo01.TestLogger")
--调用对象方法
logger:testLogger("Test call java in lua0")