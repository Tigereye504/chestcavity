$ErrorActionPreference = 'Stop'
$projectPath = Split-Path -Parent $PSScriptRoot
$sourcePath = Join-Path $projectPath 'run\gametestserver\gametestworld'
$targetPath = Join-Path $projectPath 'run\saves\chestcavity-migration-smoke'
if (-not (Test-Path -LiteralPath $sourcePath)) { throw 'Execute runGameTestServer -PwithGameTests primeiro.' }
if (Test-Path -LiteralPath $targetPath) {
    Write-Output "Mundo de teste existente: $targetPath"
    exit 0
}
New-Item -ItemType Directory -Path $targetPath -Force | Out-Null
Get-ChildItem -LiteralPath $sourcePath | Where-Object { $_.Name -notin @('session.lock','playerdata','stats','advancements') } | ForEach-Object {
    Copy-Item -LiteralPath $_.FullName -Destination $targetPath -Recurse
}
Write-Output "Mundo de teste criado: $targetPath"
