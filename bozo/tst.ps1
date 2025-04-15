param (
    [int]$ntst
)

Get-Content .\tst\$ntst.in | py .\Bozo.py 