#!/bin/env python
# This is a simple script that will compile a set of java files
# and report to the user

import sys
import tempfile
import shutil
import os
import subprocess
import os.path

files = ["Counter.java", "CounterTest.java", "ReverseArray.java", "ReverseList.java"]

supportfiles = []
supportfiledir = "/home/linux/ieng6/cs11s/public/supportfiles"

def cleanup(tmpd):
    shutil.rmtree(tmpd)

# Check if the code compiles
#  Returns (dictionary of True|False, overall true/false, errors)
def checkCompile(account,tmpd,files):
    grade = ()
    cwd = os.getcwd()
    os.chdir(tmpd)
    fail=False
    print("Checking Compilation of %s ... " % ', '.join(files))

    errors = []
    compiled = {}
    for f in files:
        cmd = "javac -classpath '*':'.':/usr/share/java/junit4.jar %s" % f
        compile = subprocess.Popen(cmd, shell=True,
            stdout=subprocess.PIPE, stderr=subprocess.PIPE)
        return_code = compile.wait()

        if (return_code):
            fail=True
            compiled[f] = False
            print("Compilation of %s failed" %f)
        else:
            compiled[f] = True
            print("Compilation of %s OK" %f)

        errors += compile.stderr.readlines()
    os.chdir(cwd)
    if (fail):
        print ("Failed Compilation Test")
        return (compiled, False, errors)
    print ("...SUCCESS")
    return (compiled, True,errors)


## Main program
for tarfile in sys.argv[1:]:
    tmpd = tempfile.mkdtemp()
    cmd = "tar xfBp %s --dir=%s" % (tarfile, tmpd)
    print ("Unpacking files ... %s" % cmd)
    os.system(cmd)

    print ("Copying support files")
    for sFile in supportfiles:
        shutil.copyfile("%s/%s" % (supportfiledir,sFile),
            "%s/%s" % (tmpd,sFile))

    # Compiles?
    (compiled, allOK, errors) = checkCompile(tarfile, tmpd, files)

    cleanup(tmpd)

    if ( not allOK ):
        sys.stdout.write("=== Errors seen while Compiling ===\n")
        for i in errors:
            sys.stdout.write(i)
        sys.stdout.write("=== End of Errors seen while Compiling ===\n")
        sys.exit(-1)
    else:
        sys.exit(0)
