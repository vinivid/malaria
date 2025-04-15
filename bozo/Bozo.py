from RolaDados import RolaDados
from Placar import Placar
from EntradaTeclado import EntradaTeclado

NRODADAS : int = 10

def main() -> None:
    print("Digite a semente (zero para aleatório): ", end="")
    seed : int = EntradaTeclado.leInt()
    rd : RolaDados = RolaDados(5, seed)
    pl : Placar = Placar()
    print(pl.toString())

    for rodada in range(0, NRODADAS):
        print(f"****** Rodada {rodada + 1}")
        print("Pressione ENTER para lançar os dados")
        EntradaTeclado.leString()

        # Primeira tentaiva
        rd.rolar()
        print("1          2          3          4          5")
        print(rd.toString())
         
        # Segunda tentaiva
        print("Digite os números dos dados que quiser TROCAR. Separados por espaços.")
        muda : str = EntradaTeclado.leString()
        rd.rolar(muda)
        print("1          2          3          4          5")
        print(rd.toString())

        # Terceira tentativa
        print("Digite os números dos dados que quiser TROCAR. Separados por espaços.")
        muda : str = EntradaTeclado.leString()
        values : list[int] = rd.rolar(muda)
        print("1          2          3          4          5")
        print(rd.toString())

        print("\n\n")
        print(pl.toString())
        
        pos : int = 0

        while pos <= 0:
            try:
                print("Escolha a posição que quer ocupar com essa jogada ===> ", end="")
                pos = EntradaTeclado.leInt()
                if (pos > NRODADAS) or (pos <= 0):
                    pos = 0
                
                pl.add(pos, values)
            except ValueError:
                print("got on exception")
                pos = 0
            
            if pos == 0:
                print("GETS ON THIS SHITHOLE")
                print("Valor inválido. Posição ocupada ou inexistente.")
        
        print("\n\n")
        print(pl.toString())

    print("***********************************")
    print("***")
    print(f"*** Seu escore final foi: {pl.getScore()}")
    print("***")
    print("***********************************")

if __name__ == '__main__':
    main()