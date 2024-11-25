[System.Net.ServicePointManager]::ServerCertificateValidationCallback = {$true}
while($true) {
    Write-Output "[Dataget-LoanDaily] Proccess time start: $(Get-Date)";
    try {
        # Invoke-Expression (New-Object System.Net.WebClient).DownloadFile("https://ifois.syariahmandiri.co.id/kappap/download_loan.php",".LOAN_DAILY.txt");
        # (New-Object System.Net.WebClient).DownloadFile("https://ifois.syariahmandiri.co.id/kappap/download_loan.php",".LOAN_DAILY.txt");
		
		$resource = "https://ifois.syariahmandiri.co.id/kappap/download_loan.php";
		$DestinationPath = ".LOAN_DAILY.txt";
		 $wc = New-Object System.Net.WebClient;
		# $wc.Credentials = New-Object System.Net.NetworkCredential($User, $Pass);
		# $wc.Headers.Add("Authorization", "Basic $encoded");
		# $wc.SetTimeout(3600);
		 $wc.DownloadFile($resource, $DestinationPath);

        # Invoke-WebRequest -Method Get -Uri $resource -cred $cred -OutFile $Destination -TimeoutSec 1800
        # Invoke-WebRequest -Method Get -Uri $resource -OutFile $DestinationPath -SkipCertificateCheck -Resume -TimeoutSec 5000

        $date = (Get-Content $DestinationPath)[1].substring(0, 10);

        $file1 = Get-Item $DestinationPath;

        $size_file1 = [string]::Format("{0:0.00} MB", $file1.Length/ 1MB);

        If (Test-Path -Path $date".LOAN_DAILY.txt") {
            $file2 = Get-Item $date".LOAN_DAILY.txt";
             $size_file2 = [string]::Format("{0:0.00} MB", $file2.Length/ 1MB);

            If ($file1.Length -gt $file2.Length)
            {
                # Make a backup copy of file on d drive
                # Copy-Item $file2 -Destination $date$DestinationPath
                Copy-Item $file1 -Destination $file2 -Force
                Write-Output "LOAN_DAILY $($date): File Size is Bigger, Replacing! (new: $($size_file1) | base: $($size_file2))";      
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
            Write-Output "LOAN_DAILY $($date): New File, Renaming! (size: $($size_file1)";
            Start-Sleep 300;
        }

        #$wc.DownloadFile($URL);
    
        Write-Output "LOAN_DAILY $($date): Succeed!";

    } catch {
    
        Write-Output "LOAN_DAILY: Failed!";
        Write-Output "Error:"$_.Exception;      
        Start-Sleep 60;

    }

    # Write-Output "Post";
}