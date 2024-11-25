$list=Get-ChildItem $PSScriptRoot
$pass=Read-Host "Please enter excel password:"
foreach ($file in $list)
{
   $n= (Get-item $file).Basename
   $ext= [System.IO.Path]::GetExtension("$file")
	IF($ext -eq ".xlsx") {		
		$x1 = New-Object -ComObject "Excel.Application"
		$x1.DisplayAlerts=$false
		$x1.Visible = $true
		$wb = $x1.workbooks.Open($file.fullname, 0, 0, 5, $pass)
		$conn = $wb.Connections()
		$wb.refreshall()
		
		$ready = 0
		while($ready -le 10) {
			Start-Sleep -Seconds 1
			IF(!$x1.Ready) {
				$ready = 0
			}
			IF($conn | ForEach-Object {if($_.OLEDBConnection.Refreshing){$true}}) {
				$ready = 0
			}
			IF($wb.Sheets | ForEach-Object {$_.QueryTables | ForEach-Object {if($_.QueryTable.Refreshing){$true}}}) {
				$ready = 0
			}
			$ready = $ready + 1
		}
		$wb.Save()
		$wb.Close()
		$x1.Quit()
		Remove-Variable wb,x1,ready,conn
		Start-Sleep -Seconds 2
	}
}