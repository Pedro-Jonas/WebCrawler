import Tasks.Task1
import Tasks.Task2
import Tasks.Task3

class Menu {
    Task1 task1 = new Task1()
    Task2 task2 = new Task2()
    Task3 task3 = new Task3()

    Scanner sc = new Scanner(System.in)

    String menu = """Digite a opção desejada: 
    1 para inciar a task 1 
    2 para inciar a task 2
    3 para inciar a task 3 
    0 para parar
    __________________________________"""

    void start() {
        println menu

        int op = sc.nextInt()

        while (op != 0) {
            switch (op){
                case 1:
                    task1.start()
                    break
                case 2:
                    task2.start()
                    break
                case 3:
                    task3.start()
                    break

                default:
                    println "Digite uma opção válida"
                    break
            }

            println menu

            op = sc.nextInt()
        }
    }
}
