from copy import deepcopy

class Placar:
    __posicoes : int = 10
    __placar : list[int] = [0 for _ in range(0, __posicoes)]
    __taken : list[bool] = [False for _ in range(0, __posicoes)]

    def getScore(self) -> int:
        return sum([x for x, y in zip(self.__placar, self.__taken) if y])

    def __conta(self, n : int, vet : list[int]) -> int:
        return sum([1 for x in vet if x == n])
    
    def __checkFull(self, dados : list[int]) -> bool:
        v : list[int] = deepcopy(dados)
        v.sort()
        check_1 = (v[0] == v[1] and v[1] == v[2] and v[2] == v[3])
        check_2 = (v[1] == v[2] and v[2] == v[3] and v[3] == v[4])
        return check_1 or check_2
    
    def __checkQuadra(self, dados : list[int]) -> bool:
        v : list[int] = deepcopy(dados)
        v.sort()
        check_1 = (v[0] == v[1] and v[1] == v[2] and v[2] == v[3])
        check_2 = (v[1] == v[2] and v[2] == v[3] and v[3] == v[4])
        return check_1 or check_2
    
    def __checkQuina(self, dados : list[int]) -> bool:
        v : list[int] = dados.copy()
        return (v[0] == v[1] and v[1] == v[2] and v[2] == v[3] and v[3] == v[4])

    def __checkSeqMaior(self, dados : list[int]) -> bool:
        v : list[int] = deepcopy(dados)
        v.sort()

        return (v[0] == v[1]-1 and v[1] == v[2]-1 and v[2] == v[3]-1 and v[3] == v[4]-1)

    def add(self, posicao : int, dados : list[int]) -> None:
        if self.__taken[posicao - 1]:
            raise ValueError('Posição ocupada')
        
        k : int = 0
        match posicao:
            case 1 | 2 | 3 | 4 | 5 | 6:
                k = posicao * self.__conta(posicao, dados)
            case 7:
                if self.__checkFull(dados) : 
                    k = 15
            case 8:
                if self.__checkSeqMaior(dados):
                    k = 20
            case 9:
                if self.__checkQuadra(dados):
                    k = 30
            case 10:
                if self.__checkQuina(dados):
                    k = 40
            case _:
                raise ValueError('Valor da posição ilegal')
        
        self.__placar[posicao - 1] = k
        self.__taken[posicao - 1] = True
    
    def toString(self) -> str:
        num : str
        s : str = ''
        for i in range(0, 3):
            if self.__taken[i]:
                num = f' {self.__placar[i]:<3}'
            else:
                num = f'({i + 1}) '

            if self.__taken[i + 6]:
                s += num + '   |    '
                num = f'{self.__placar[i + 6]:<3}'
            else:
                s += num + '   |   '
                num = num = f'({i + 7}) '


            if self.__taken[i + 3]:
                s += num + '   |   '
                num = f'{self.__placar[i + 3]:<3}'
            else:
                s += num + '   |  '
                num = num = f'({i + 4}) '
            
            s+= num + '\n-------|----------|-------\n'
        
        if self.__taken[9]:
            num = f'{self.__placar[9]:<3}'
            s += "       |    " + num + "   |"
        else:
            num = "(10)"
            s += "       |   " + num + "   |"

        s += "\n       +----------+\n"
        return s