package tgl.cache

class TglMemoryCache implements tglCache {

  def cache = [:]

  String get(String code) {
    cache[code]
  }

  void put(String code, String value) {
    cache[code] = value
  }

  void clean() {
    cache.clear()
  }

  void remove(String code) {
    cache.remove(code)
  }

}
