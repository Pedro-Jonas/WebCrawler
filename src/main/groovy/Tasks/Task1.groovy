package Tasks

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

            String urlDowload = ''
            table.each {element ->
                urlDowload =  element.child(4).select('a[href]').attr('href')
            }


            println urlDowload

        } catch (HttpException e) {
            e.printStackTrace()
        }
    }
}