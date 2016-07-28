package me.hub.atualizar;

import java.io.PrintStream;

import me.hub.API.Util.UtilTime;

public enum ModosUpdate
{

	  HORAS_12(43200000L), 
	  HORAS_5(18000000L),
	  HORAS_3(10800000L),
	  HORAS_1(3600000L),
	  MIN_64(3840000L),
	  MIN_32(1920000L), 
	  MIN_16(960000L), 
	  MIN_08(480000L), 
	  MIN_04(240000L), 
	  MIN_02(120000L), 
	  MIN_01(60000L),
	  SEC_30(30000L),
	  SEC_20(20000L),
	  SEC_10(10000L),
	  SEC(1000L),
	  SLOWEST(32000L), 
	  SLOWER(16000L), 
	  SLOW(4000L),  
	  FAST(500L), 
	  FASTER(250L), 
	  FASTEST(125L), 
	  TICK(49L);

  private long _time;
  private long _last;
  private long _timeSpent;
  private long _timeCount;

  private ModosUpdate(long time) { this._time = time;
    this._last = System.currentTimeMillis();
  }

  public boolean Elapsed()
  {
    if (UtilTime.decorrido(this._last, this._time))
    {
      this._last = System.currentTimeMillis();
      return true;
    }

    return false;
  }

  public void StartTime()
  {
    this._timeCount = System.currentTimeMillis();
  }

  public void StopTime()
  {
    this._timeSpent += System.currentTimeMillis() - this._timeCount;
  }

  public void PrintAndResetTime()
  {
    System.out.println(name() + " segundos: " + this._timeSpent);
    this._timeSpent = 0L;
  }
}