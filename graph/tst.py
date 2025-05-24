import os 
import subprocess

#Diretorio no qual os casos testes estao localizados
TEST_DIR = './tests'
# Diretorio no qual tera a saida do programa, dentro dele tem de ter uma pasta chamda cmp
PROGRAM_OUTPUT_DIR = './prg_out'

test_files   = os.listdir(TEST_DIR)
input_files  = [x for x in test_files if x.endswith('.in')]
expected_out = [x.removesuffix('.out') for x in test_files if x.endswith('.out')]
expected_out.sort(key=int)
expected_out = [f'{x}.out' for x in expected_out]

for file in input_files:
    with open(f'{TEST_DIR}/{file}', 'r', encoding='utf-8') as inp_file:
        with open(f'{PROGRAM_OUTPUT_DIR}/{file.removesuffix(".in")}.pgo', 'w', encoding='utf-8') as program_output:
            # Programa a rodar para testar, onde a lista no primeiro argumento é o o programa a ser rodado
            # exemplo para um programa compilado sem argumentos
            # subprocess.run([programa], stdin=inp_file, stdout=program_output)
            subprocess.run(['py', 'Main.py'], stdin=inp_file, stdout=program_output)

number_passed = 0
for num, file in enumerate(expected_out):
    with open(f'{PROGRAM_OUTPUT_DIR}/cmp/{file.removesuffix(".out")}.cmp', 'w', encoding='utf-8') as cmp:
        # Se vc esta no linux mude de 'diff.exe' para 'diff'
        ok = subprocess.run(['diff.exe', 
                        f'{TEST_DIR}/{file}', 
                        f'{PROGRAM_OUTPUT_DIR}/{file.removesuffix(".out")}.pgo'],   
                        capture_output=True,
                        text=True)
        cmp.write(str(ok.stdout))
        if ok.returncode == 0:
            number_passed += 1
            print(f'Test {num + 1} : \033[0;32m Passed \033[0;38m')
        else:
            print(f'Test {num + 1} :\033[0;31m Fail \033[0;38m')

print(f'Passed {number_passed}/{len(expected_out)}')