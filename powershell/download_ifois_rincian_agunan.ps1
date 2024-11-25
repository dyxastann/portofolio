[System.Net.ServicePointManager]::ServerCertificateValidationCallback = {$true}
while($true) {
    Write-Output "[Dataget-Agunan] Proccess time start: $(Get-Date)";
    try {
		
		$resource = "https://ifois.syariahmandiri.co.id/kappap/download_agunan.php";
		$DestinationPath = ".RINCIAN_AGUNAN.txt";
		 $wc = New-Object System.Net.WebClient;
		 $wc.DownloadFile($resource, $DestinationPath);

        $date = (Get-Content $DestinationPath)[1].substring(0, 10);

        $file1 = Get-Item $DestinationPath;

        $size_file1 = [string]::Format("{0:0.00} MB", $file1.Length/ 1MB);

        If (Test-Path -Path $date".RINCIAN_AGUNAN.txt") {
            $file2 = Get-Item $date".RINCIAN_AGUNAN.txt";
             $size_file2 = [string]::Format("{0:0.00} MB", $file2.Length/ 1MB);

            If ($file1.Length -gt $file2.Length)
            {
                # Make a backup copy of file on d drive
                # Copy-Item $file2 -Destination $date$DestinationPath
                Copy-Item $file1 -Destination $file2 -Force
                Write-Output "RINCIAN_AGUNAN $($date): File Size is Bigger, Replacing! (new: $($size_file1) | base: $($size_file2))";      
                Start-Sleep 300;
            } else {
                If ($file1.Length -eq $file2.Length)
				{
					# Make a backup copy of file on d drive
					# Copy-Item $file2 -Destination $date$DestinationPath
					# Copy-Item $file1 -Destination $file2 -Force
					Remove-Item $file1;
					Write-Output "LOAN_DAILY $($date): File Size is Same, Congrats! (new: $($size_file1) | base: $($size_file2))";      
					Start-Sleep 300;
				} else {
					Remove-Item $file1;
					Write-Output "LOAN_DAILY $($date): File Size is Smaller, Removing! (new: $($size_file1) | base: $($size_file2)))";      
					Start-Sleep 60;
				}
            }
        } else {
            Rename-Item -Path $DestinationPath -NewName $date$DestinationPath;
            Write-Output "RINCIAN_AGUNAN $($date): New File, Renaming! (size: $($size_file1)";
            Start-Sleep 300;
        }

        #$wc.DownloadFile($URL);
    
        Write-Output "RINCIAN_AGUNAN $($date): Succeed!";

    } catch {
    
        Write-Output "RINCIAN_AGUNAN: Failed!";
        Write-Output "Error:"$_.Exception;      
        Start-Sleep 60;

    }

    # Write-Output "Post";
}