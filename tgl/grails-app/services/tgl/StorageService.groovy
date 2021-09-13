package tgl

import grails.gorm.transactions.Transactional

@Transactional
class StorageService {



    def configurationService

    def init() {
        diretorio().mkdirs()
    }

    def diretorio() {
        new File(configurationService.info('storage.folder'))
    }

    InputStream readFilename(String filename) {
        File arquivo = new File(diretorio().getAbsolutePath() + "/${filename}")
        new FileInputStream(arquivo)
    }

    Arquivo store(String originalFilename, InputStream input) {
        def componentes = originalFilename.split("\\.")
        def chave = UUID.randomUUID().toString() + "." + componentes[componentes.length - 1]
        def result = new Arquivo(chave:chave, nomeOriginal:originalFilename, bucket:diretorio().getAbsolutePath())
        def arquivoSaida = new File(diretorio().getAbsolutePath() + "/${chave}")
        byte[] buffer = new byte[16384]
        int c = -1;
        def outputStream = new FileOutputStream(arquivoSaida)
        while ((c = input.read(buffer)) != -1) {
            outputStream.write(buffer, 0, c)
        }
        outputStream.close()
        def registro = new Arquivo()
        registro.chave = chave
        registro.nomeOriginal = originalFilename
        registro.bucket = 'filesystem'
        registro.dataInclusao = new Date()
        return registro.save(failOnError:true, flush:true)

    }




}
