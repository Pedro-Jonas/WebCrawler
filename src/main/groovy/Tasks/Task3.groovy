package Tasks

import groovyx.net.http.optional.Download
import org.jsoup.select.Elements
import org.jsoup.nodes.Document

import static groovyx.net.http.HttpBuilder.configure
import groovyx.net.http.HttpException


class Task3 {

    static void start() {
        try {
            Document page = configure {
                request.uri = 'https://www.gov.br/ans/pt-br/assuntos/prestadores/' +
                        'padrao-para-troca-de-informacao-de-saude-suplementar-2013-tiss/' +
                        'padrao-tiss-tabelas-relacionadas'
            }.get() as Document

            Elements table  = page.select("#content-core")

            Elements links = table.select("p")

            String url = links[0].select('a[href]').attr('href')

            if (url) {
                download(url)
            } else {
                println "Url para Download não encontrada! :("
            }

        } catch (HttpException e) {
            e.printStackTrace()
        }
    }

    private static void download(String url){
        try {
            String path = "/home/pedrojonas/Documentos/Projetos/WebCrawler/downloads/padrao_TISS/task3"

            File file = new File(path, "erros.xlsx" )

            configure {
                request.uri = url
            }.get {
                Download.toFile(delegate, file)
            } as File

        } catch (Exception e) {
            e.printStackTrace()
        }
    }
}
