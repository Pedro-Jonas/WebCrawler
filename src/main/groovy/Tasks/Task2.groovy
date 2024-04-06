package Tasks

import org.jsoup.select.Elements
import org.jsoup.nodes.Document

import static groovyx.net.http.HttpBuilder.configure
import groovyx.net.http.HttpException


class Task2 {

    static void start() {
        try {
            Document page = configure {
                request.uri = 'https://www.gov.br/ans/pt-br/assuntos/prestadores/' +
                        'padrao-para-troca-de-informacao-de-saude-suplementar-2013-tiss/' +
                        'padrao-tiss-historico-das-versoes-dos-componentes-do-padrao-tiss'
            }.get() as Document

            Elements table = page.getElementsByTag("tbody")

            writeTable(table)
        } catch (HttpException e) {
            e.printStackTrace()
        }
    }

    static void writeTable(Elements table){
        try {
            int size = table[0].childrenSize()

            String path = "/home/pedrojonas/Documentos/Projetos/WebCrawler/downloads/padrao_TISS/task2"

            File file = new File(path, "historico.csv")

            file.createNewFile()

            def writer = new FileWriter(file, true)

            writer.write("competência,publicação,início de vigência\n")

            for(int i = 0; i < size; i++ ){
                String competencia =  table[0].child(i).child(0).text()
                String publicacao =  table[0].child(i).child(1).text()
                String vigencia =  table[0].child(i).child(2).text()

                writer.write("${competencia},${publicacao},${vigencia}\n")

                if (competencia == "Jan/2016"){
                    break
                }
            }

            writer.close()
        } catch (Exception e) {
            e.printStackTrace()
        }

    }
}
