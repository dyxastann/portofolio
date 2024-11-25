$list=Get-ChildItem $PSScriptRoot
foreach ($file in $list)
{
   $n= (Get-item $file).Basename
   $ext= [System.IO.Path]::GetExtension("$file")
	IF($ext -eq ".txt") {		
		$converted = (Get-Content "$file")
		Set-Content -Encoding utf8 "$file" -value $converted
		Start-Sleep -Seconds 2
        IF($n.substring(0, 18) -eq "DAILY_PAYMENT_MCS_"){
            IF((Get-Content $file)[0] -ne "NOLOAN *),Outlet Baru,Nama Outlet Baru,Area Remapping,Pay Pokok *),Pay Margin *),Pay Total *),Tgl Bayar (dd/MM/yyyy) *)") {
                "NOLOAN *),Outlet Baru,Nama Outlet Baru,Area Remapping,Pay Pokok *),Pay Margin *),Pay Total *),Tgl Bayar (dd/MM/yyyy) *)`
"+(Get-Content $file -Raw) | Set-Content $file;
            }
             Rename-Item -Path $file -NewName $n".csv";
            # Import-csv -Path $file  -Delimiter "," -Header "Tgl Data", "LOAN_T24", "NOLOAN", "NOMORCIF", "KOLCIF", "Pembayaran Pokok", "Pembayaran Margin", "Pembayaran Basil" | Export-xlsx -Path $n.xlsx
            
            
            $Excel = New-Object -ComObject Excel.Application
            # $Excel.Visible = $false
            $Excel.DisplayAlerts = $false;
            $Item = Get-Item $n".csv";
            $WorkBook = $Excel.Workbooks.Open($Item.FullName);
            # $WorkBook.ActiveSheet.range("A1").EntireColumn.texttocolumns($WorkBook.ActiveSheet.range("A1"),1,1,$true,$false,$false,$true,$false);
            # $WorkBook = $WorkBook |ConvertFrom-Csv;
            $NewFilepath = $file.FullName -replace ".{4}$";
            $NewFilepath =  $NewFilepath + ".xls";
            $Workbook.SaveAs($NewFilepath, 56);
            # Stop-Process -ProcessName EXCEL;
            $Excel.Quit();
            
            Remove-Item $Item;
        }
	}
}