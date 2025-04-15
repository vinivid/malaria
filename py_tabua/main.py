from Table import Table

def main():
    values : list[int] = [int(x, 10) for x in input().split()]
    game_table = Table(values)
    print(f'Posicao final: {game_table.aplly_solution(input())}')

if __name__ == '__main__':
    main()