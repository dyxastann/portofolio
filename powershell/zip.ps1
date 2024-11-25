$list=Get-ChildItem $PSScriptRoot
$pass=Read-Host "Please enter zip password:"
foreach ($file in $list)
{
	$pathToCustom7Zip = "D:\Installed Apps\7-Zip\7z.exe"
    $pathTo32Bit7Zip = "C:\Program Files (x86)\7-Zip\7z.exe"
    $pathTo64Bit7Zip = "C:\Program Files\7-Zip\7z.exe"
	
	if (Test-Path $pathTo64Bit7Zip) { $pathTo7ZipExe = $pathTo64Bit7Zip }
    elseif (Test-Path $pathTo32Bit7Zip) { $pathTo7ZipExe = $pathTo32Bit7Zip }
    elseif (Test-Path $pathToCustom7Zip) { $pathTo7ZipExe = $pathToCustom7Zip }
    else { throw "Could not find the 7-zip executable." }
	
   $n= (Get-item $file).Basename
   $ext= [System.IO.Path]::GetExtension("$file")
	IF($ext -eq ".xlsx") {
		if (Test-Path "$n.zip") { Remove-Item "$n.zip" -Force }
		$file1=$file|Select-Object -ExpandProperty name
		& $pathTo7ZipExe a "$n.zip" $file1 -p($pass)
	}
}