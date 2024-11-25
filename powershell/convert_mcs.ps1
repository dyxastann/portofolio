$list=Get-ChildItem $PSScriptRoot
foreach ($file in $list)
{
    $n= (Get-item $file).Basename
	$filename = $n.replace("_ByBranch_BSI Collection", "").replace("_ByBranch_BSM Collection", "").replace("Collection Result By Branch_ByBranch_", "").replace("_FORM-BSM Collection_BRANCH-ALL_SPV-ALL_USER-ALL", "")
	$ext= [System.IO.Path]::GetExtension("$file")
	IF($ext -eq ".csv") {
		$converted = (Get-Content $file).replace("`"=`"`"", "`"").replace("`"`"`"", "`"").replace("`"APAKAH PEKERJAAN VALID`?`",`"PEKERJAAN`",`"ALAMAT PEKERJAAN`"", "`"APAKAH PEKERJAAN VALID`?`",`"PEKERJAAN_1`",`"ALAMAT PEKERJAAN`"").replace("`"COLLECTION RESULT`",`"COLLECTION RESULT`"", "`"COLLECTION RESULT`",`"COLLECTION RESULT_2`"")
		Set-Content -path "IMPORT_$filename.txt" -value $converted 
	}
}