import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.apache.commons.compress.utils.Sets;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateRange;
import cn.hutool.core.date.DateTime;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateTimeDemo01 {
  public static void main(String[] args) {
    Set<String> projectTypes = Sets.newHashSet("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
    List<Amount> datas = new ArrayList<>();
    // 准备假数据
    for (int i = 0; i < 10; i++) {
      datas.add(new Amount(i + 1 + "", BigDecimal.valueOf(i)));
    }
    projectTypes.forEach(e -> {
      // 获取到每个类型的值
      Double v = datas.stream()
          //
          .filter(it -> it.getType().equals(e))
          //
          .findFirst()
          // map
          .map(it -> it.getValue().doubleValue())
          // orelse
          .orElse(0.0);
      System.out.println(v);
    });

  }

  private static void mapToDouble() {
    List<Amount> datas = new ArrayList<>();
    // 准备假数据
    for (int i = 0; i < 10; i++) {
      datas.add(new Amount(1 + "", BigDecimal.valueOf(i)));
    }

    // 求和
    double value = datas.stream()
        // DoubleStream.ToDoubleFunction
        .mapToDouble(it -> it.getValue().doubleValue())
        //
        .sum();
    log.info("{}", value);
  }

  private static void localData() {

    LocalDate localDate = LocalDate.parse("2022-10-01");
    System.out.println(localDate);
  }

  private static void groupBy() {
    List<Student> values = new ArrayList<>();
    for (int i = 0; i < 12; i++) {
      for (int j = 0; j < 4; j++) {
        values.add(new Student(i + 1, "张三" + i, i + 10));
      }
    }
    log.info("{}", values.size());

    // 对假数据根据班级进行分类
    Map<Integer, List<Student>> collect = values.stream().collect(Collectors.groupingBy(Student::getClazz));
    log.info("{}", collect.size());
    System.out.println(collect);
  }

  private static void toListString() {
    Date startDate = new DateTime().offset(DateField.MONTH, -12).toJdkDate();
    Date endDate = new DateTime().offset(DateField.MONTH, -1).toJdkDate();

    DateRange dateRange = new DateRange(startDate, endDate, DateField.MONTH);

    // 将dateRange转为
    List<DateTime> dateTimeList = CollUtil.newArrayList((Iterable<DateTime>) dateRange);

    List<String> listString = dateTimeList.stream().map(e -> format(e)).collect(Collectors.toList());

    for (String string : listString) {
      System.out.println(string);
    }
  }

  private static String format(DateTime dateTime) {
    TimeZone timeZone = dateTime.getTimeZone();

    String format = "yyyy-MM";
    SimpleDateFormat simpleFormat = newSimpleFormat(format, null, timeZone);
    String string = simpleFormat.format(dateTime);
    return string;
  }

  /**
   * 创建{@link SimpleDateFormat}，注意此对象非线程安全！<br>
   * 此对象默认为严格格式模式，即parse时如果格式不正确会报错。
   *
   * @param pattern  表达式
   * @param locale   {@link Locale}，{@code null}表示默认
   * @param timeZone {@link TimeZone}，{@code null}表示默认
   * @return {@link SimpleDateFormat}
   * @since 5.5.5
   */
  public static SimpleDateFormat newSimpleFormat(String pattern, Locale locale, TimeZone timeZone) {
    if (null == locale) {
      locale = Locale.getDefault(Locale.Category.FORMAT);
    }
    final SimpleDateFormat format = new SimpleDateFormat(pattern, locale);
    if (null != timeZone) {
      format.setTimeZone(timeZone);
    }
    format.setLenient(false);
    return format;
  }

  private static void getDateTimeList() {
    Date startDate = new DateTime().offset(DateField.MONTH, -12).toJdkDate();
    Date endDate = new DateTime().offset(DateField.MONTH, -1).toJdkDate();
    log.info(startDate.toString());
    log.info(endDate.toString());

    DateRange dateRange = new DateRange(startDate, endDate, DateField.MONTH);
    log.info("dateRange:{}", dateRange);

    // 将dateRange转为
    ArrayList<DateTime> newArrayList = CollUtil.newArrayList((Iterable<DateTime>) dateRange);
    log.info("size:{}", newArrayList.size());
    for (DateTime d : newArrayList) {
      log.info(d.toString());
    }
  }

  private static void lastMonth() {
    DateTime dateTime = new DateTime();
    // 月份减去1
    dateTime.offset(DateField.MONTH, -1);
    // 转为jdk的时间
    Date jdkDate = dateTime.toJdkDate();
    log.info("jdkDate:{}", jdkDate);
  }
}
