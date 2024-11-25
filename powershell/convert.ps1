$list=Get-ChildItem $PSScriptRoot
foreach ($file in $list)
{
   $n= (Get-item $file).Basename
   $ext= [System.IO.Path]::GetExtension("$file")
	IF($ext -eq ".txt") {		
		$converted = (Get-Content "$file")
		Set-Content -Encoding utf8 "$file" -value $converted
		Start-Sleep -Seconds 2
	}
}