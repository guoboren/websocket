package websocket.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil
{
  public static String getTimeString()
  {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return sdf.format(new Date());
  }
}
