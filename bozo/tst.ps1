param (
    [int]$ntst
)

Get-Content .\tst\$ntst.in | py .\Bozo.py | Out-File -FilePath .\ok.txt