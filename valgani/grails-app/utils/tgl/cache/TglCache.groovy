package tgl.cache

interface TglCache {

  /**
    Retorna uma string armazenada no cache
  */
  String get(String code)

  /**
    Armazena uma string no cache
  */
  void put(String code, String value)

  /**
    Remove um item do cache
  */
  void remove(String code)

  /**
    Limpa todas as chaves do cache
  */
  void clean()

}
