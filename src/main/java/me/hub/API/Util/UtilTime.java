package me.hub.API.Util;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class UtilTime
{
  public static final String FormatoCompleto = "yyyy-MM-dd HH:mm:ss";
  public static final String FormatoDoDia = "yyyy-MM-dd";

  
  //Formata��o de tempo Java 
  
  public static String Normal()
  {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return sdf.format(cal.getTime());
  }

  public static String DiaHorario(long time)
  {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return sdf.format(Long.valueOf(time));
  }

  public static String Data()
  {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    return sdf.format(cal.getTime());
  }

  public static String since(long epoch)
  {
    return "Took " + Convertor(System.currentTimeMillis() - epoch, 1, TimeUnit.FIT) + ".";
  }

  public static double FormatarTempo(long time, int trim, TimeUnit type)
  {
    if (type == TimeUnit.FIT)
    {
      if (time < 60000L) type = TimeUnit.SECONDS;
      else if (time < 3600000L) type = TimeUnit.MINUTES;
      else if (time < 86400000L) type = TimeUnit.HOURS; else {
        type = TimeUnit.DAYS;
      }
    }
    if (type == TimeUnit.DAYS) return UtilMath.trim(trim, time / 86400000.0D);
    if (type == TimeUnit.HOURS) return UtilMath.trim(trim, time / 3600000.0D);
    if (type == TimeUnit.MINUTES) return UtilMath.trim(trim, time / 60000.0D);
    if (type == TimeUnit.SECONDS) return UtilMath.trim(trim, time / 1000.0D);
    return UtilMath.trim(trim, time);
  }

  public static String MakeStr(long time)
  {
    return Convertor(time, 1, TimeUnit.FIT);
  }

  public static String MakeStr(long time, int trim)
  {
    return Convertor(time, trim, TimeUnit.FIT);
  }
  public static String convertres(long d, int trim, TimeUnit type)
  {
    if (d == -1L) return "Permanent";

    if (type == TimeUnit.FIT)
    {
      if (d < 60000L) type = TimeUnit.SECONDS;
      else if (d < 3600000L) type = TimeUnit.MINUTES;
      else if (d < 86400000L) type = TimeUnit.HOURS;
      else if (d >= 31536000000L) type = TimeUnit.ANO;
      else if (d >= 33155692597470L) type = TimeUnit.SECULO;
      else {
        type = TimeUnit.DAYS;
      }
    }
    if (type == TimeUnit.SECULO) return UtilMath.trim(trim, d / 315569259747.0D) + " Seculo";
    if (type == TimeUnit.ANO) return UtilMath.trim(trim, d / 31536000000.0D) + " Ano";
    if (type == TimeUnit.MES) return UtilMath.trim(trim, d / 259200000.0D) + " Mês";
    if (type == TimeUnit.DAYS) return UtilMath.trim(trim, d / 86400000.0D) + " Dias";
    if (type == TimeUnit.HOURS) return UtilMath.trim(trim, d / 3600000.0D) + " H";
    if (type == TimeUnit.MINUTES) return UtilMath.trim(trim, d / 60000.0D) + " Min";
    if (type == TimeUnit.SECONDS) return UtilMath.trim(trim, d / 1000.0D) + " Seg";
    return UtilMath.trim(trim, d) + " Milissegundos";
  }
  
  public static String convertString(long d, int trim, TimeUnit type)
  {
    if (d == -1L) return "Permanent";

    if (type == TimeUnit.FIT)
    {
      if (d < 60000L) type = TimeUnit.SECONDS;
      else if (d < 3600000L) type = TimeUnit.MINUTES;
      else if (d < 86400000L) type = TimeUnit.HOURS;
      else if (d >= 31536000000L) type = TimeUnit.ANO;
      else if (d >= 33155692597470L) type = TimeUnit.SECULO;
      else {
        type = TimeUnit.DAYS;
      }
    }
    if (type == TimeUnit.SECULO) return UtilMath.trim(trim, d / 315569259747.0D) + " Seculo";
    if (type == TimeUnit.ANO) return UtilMath.trim(trim, d / 31536000000.0D) + " Ano";
    if (type == TimeUnit.MES) return UtilMath.trim(trim, d / 259200000.0D) + " Mês";
    if (type == TimeUnit.DAYS) return UtilMath.trim(trim, d / 86400000.0D) + " Dias";
    if (type == TimeUnit.HOURS) return UtilMath.trim(trim, d / 3600000.0D) + " Horas";
    if (type == TimeUnit.MINUTES) return UtilMath.trim(trim, d / 60000.0D) + " Minutos";
    if (type == TimeUnit.SECONDS) return UtilMath.trim(trim, d / 1000.0D) + " Segundos";
    return UtilMath.trim(trim, d) + " Milissegundos";
  }
  
  public static String Convertor(double d, int trim, TimeUnit type)
  {
    if (d < 0) return "Permanente";

    if (type == TimeUnit.FIT)
    {
      if (d < 60000L) type = TimeUnit.SECONDS;
      else if (d < 3600000L) type = TimeUnit.MINUTES;
      else if (d < 86400000L) type = TimeUnit.HOURS; else {
        type = TimeUnit.DAYS;
      }
    }
    if (type == TimeUnit.DAYS) return UtilMath.trim(trim, d / 86400000.0D) + " Dia";
    if (type == TimeUnit.HOURS) return UtilMath.trim(trim, d / 3600000.0D) + " Hora";
    if (type == TimeUnit.MINUTES) return UtilMath.trim(trim, d / 60000.0D) + " Minutos";
    if (type == TimeUnit.SECONDS) return UtilMath.trim(trim, d / 1000.0D) + " Segundos";
    return UtilMath.trim(trim, d) + " Milisegundo";
  }
  
  public static String ConvertorVIP(long time, int trim, TimeUnit type)
  {
    if (time < 0) return "Permanente";

    if (type == TimeUnit.FIT)
    {
      if (time < 60000L) type = TimeUnit.SECONDS;
      else if (time < 3600000L) type = TimeUnit.MINUTES;
      else if (time < 86400000L) type = TimeUnit.HOURS; else {
        type = TimeUnit.DAYS;
      }
    }
    if (type == TimeUnit.DAYS) return UtilMath.trim(trim, time / 86400000.0D) + " Dia";
    return UtilMath.trim(trim, time) + " Milisegundo";
  }

  public static boolean decorrido(long from, long required)
  {
    return System.currentTimeMillis() - from > required;
  }

  public static enum TimeUnit
  {
    FIT, 
    SECULO,
    ANO,
    MES,
    DAYS, 
    HOURS, 
    MINUTES, 
    SECONDS, 
    MILLISECONDS;
  }
  
  public static boolean elapsed(long from, long required)
  {
    return System.currentTimeMillis() - from > required;
  }
}