package Tasks

import groovyx.net.http.optional.Download
import org.jsoup.select.Elements
import org.jsoup.nodes.Document

import static groovyx.net.http.HttpBuilder.configure
import groovyx.net.http.HttpException


class Task1 {

    static void start() {
        try {
            Document page = configure {
                request.uri = 'https://www.gov.br/ans/pt-br/assuntos/prestadores/' +
                        'padrao-para-troca-de-informacao-de-saude-suplementar-2013-tiss/' +
                        'marco-2024'
            }.get() as Document

            Elements table = page.getElementsByTag("tbody")

            String url = ''

            table.each {element ->
                url =  element.child(4).select('a[href]').attr('href')
            }

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
            String path = "/home/pedrojonas/Documentos/Projetos/WebCrawler/downloads/padrao_TISS/task1"

            File file = new File(path, "comunicacao.zip" )

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