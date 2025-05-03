with open('10.in', 'r') as fl:
    for line in fl:
        a = line.split()
        if a[0] == 'i':
            print(f"ok.insert({'\"' + a[1] + '\"'});")
        else:
            print(f"ok.remove({'\"' + a[1] + '\"'});")
