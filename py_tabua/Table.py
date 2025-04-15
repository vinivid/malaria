from math import sqrt
from copy import deepcopy

class Table:
    game = []
    size = 0
    zero_index = 0

    def __init__ (self, table : list[int]) -> None:
        self.game = table
        self.size = int(sqrt(len(table)))
        
        for i in range(0, len(table)):
            if table[i] == 0:
                self.zero_index = i
                break

    def __swap_pos_zero(self, new_pos : int) -> None:
        self.game[self.zero_index] = self.game[new_pos]
        self.game[new_pos] = 0
        self.zero_index = new_pos

    def __up(self) -> None:
        if (self.zero_index // self.size) == self.size - 1:
            return

        new_pos : int = self.zero_index + self.size;
        self.__swap_pos_zero(new_pos)


    def __down(self) -> None:
        if (self.zero_index // self.size) == 0:
            return

        new_pos : int = self.zero_index - self.size;
        self.__swap_pos_zero(new_pos);

    def __left(self) -> None:
        if (self.zero_index % self.size) == self.size - 1:
            return

        new_pos : int = self.zero_index + 1;
        self.__swap_pos_zero(new_pos);

    def __right(self) -> None:
        if ( (self.zero_index % self.size) == 0):
            return

        new_pos : int = self.zero_index - 1;
        self.__swap_pos_zero(new_pos);

    def check_if_solved(self) -> bool:
        ordered = deepcopy(self.game)
        ordered.sort()

        for a, b in zip(self.game, ordered):
            if a != b:
                return False
        
        return True
        
    def print_current_state (self) -> None: 
        print("+", end="");

        for _ in range(0, self.size):
            print("------+", end="")
        
        print("\n", end="")

        for  i  in range(0, self.size):
            print("|", end="")

            for j in range(0, self.size):
                print_val : int = self.game[i * self.size + j]

                if print_val == 0:
                    print(f"      |", end="")
                elif print_val <= 9:
                    print(f"   {print_val}  |", end="")
                else:
                    print(f"  {print_val}  |", end="")

            print("\n", end="")
            print("+", end="")

            for _ in range(0, self.size):
                print("------+", end="")

            print("\n", end="")
         
        print("\n", end="");

    def aplly_solution (self, sol : str) -> bool:
        self.print_current_state()
        
        for l in sol:
            
            match l:
                case 'u':
                    self.__up()    

                case 'd':
                    self.__down()

                case 'l':
                    self.__left()

                case 'r':
                    self.__right()

                case _:
                    return
            
            self.print_current_state()
        
        return self.check_if_solved()