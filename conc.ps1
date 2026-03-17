# Define the root folder
$rootFolder = "C:\Code\Java\HospitalManagement"

# Function to concatenate .java files in a folder and its subfolders
function ConcatenateJavaFiles($folder, $outputFile) {
    Get-ChildItem $folder -Recurse -Filter *.java | 
    Get-Content | 
    Out-File $outputFile -Append
}

# Iterate over each subfolder in the root folder
Get-ChildItem $rootFolder -Directory | ForEach-Object {
    $subFolder = $_.FullName


    # Get the first subfolder inside the current subfolder (unknown name)
    $firstSubFolder = Get-ChildItem $subFolder -Directory | Select-Object -First 1


    # Check if the first subfolder exists
    if ($firstSubFolder) {
        $firstSubFolderName = $firstSubFolder.Name

        # Construct the target folder path
        $targetFolder = Join-Path $subFolder "$firstSubFolderName"
        
    	# Print the current folder being processed
    	Write-Host "Processing folder: $targetFolder"
	Write-Host Test-Path $targetFolder

	if (Test-Path $targetFolder) {
            # Path for the output file
            $outputFile = Join-Path $subFolder "AllJavaFilesConcatenated.txt"
            
            # Call the function to concatenate java files
	    Write-Host $targetFolder $outputFile
            ConcatenateJavaFiles $targetFolder $outputFile
        }
    }
}