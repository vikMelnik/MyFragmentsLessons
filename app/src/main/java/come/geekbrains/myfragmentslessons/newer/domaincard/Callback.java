package come.geekbrains.myfragmentslessons.newer.domaincard;

public interface Callback <T>{

  void onSuccess(T data);

  void onError(Throwable exception);
}
