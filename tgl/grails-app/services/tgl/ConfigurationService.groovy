package tgl


import javax.annotation.PostConstruct
import grails.util.Environment


class ConfigurationService {

    def tglCacheService

    /*
      Load all the default configurations
    */
    void init() {
      def resource = getClass().getClassLoader().getResourceAsStream("config-${Environment.current.getName()}.properties")
      if (resource) {
        def properties = new Properties()
        properties.load(resource)

        Configuration.withSession {session ->
          for (String key in properties.stringPropertyNames()) {
            if (! Configuration.findByCode(key)) {
              new Configuration(code:key, value:properties.getProperty(key)).save(failOnError:true)
            }
          }
        }

      }
    }

    private String getKey(String code) {
      "tgl.configuration.${code}"
    }

    def update(String code, String value) {
      def configuration = Configuration.findByCode(code)
      if (! configuration) {
        configuration = new Configuration(code:code, value:value)
      } else {
        configuration.value = value
      }
      configuration.save(failOnError:true, flush:true)
      tglCacheService.put(getKey(code), value)
    }

    String info(String code) {
      String key = getKey(code)
      String cached = tglCacheService.get(key)
      if (cached) {
        return cached
      }

      String result = Configuration.findByCode(code)?.value

      tglCacheService.put(key, result)

      result
    }

    String info(String code, String defaultValue) {
      String result = info(code)
      result ?: defaultValue
    }

}
