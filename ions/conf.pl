#!\usr\bin\perl
use File::Copy;
BEGIN{@ARGV=map{glob($_)}@ARGV}


# extracts conf from table and writes to file


  foreach (@ARGV)

  {

   $file=$_;

   $conf="0";$F2=0;$F4=0;$F6=0;$xi=0;
   ($confread)=extract("conf",$file);
   print "<".$file." read conf $confread>\n";

# compare to scatteringlength from table
   unless (open (Fin, "pars.dat")){die "\n error:unable to open $file\n";}   

$element=$file;
# $element=~s/p\./\+\./;
$element=~s/\.sipf//;
$elemen=~s/\u/\l/;
print $element."\n";

while($line=<Fin>)
      {if($line=~/^\s*$element\s/i) 
        {
($conf)=($line=~m|conf\s*=\s*(.[\d.eEdD\Q-\E\Q+\E]+)|);
($F2)=($line=~m|F2\s*=\s*([\d.eEdD\Q-\E\Q+\E]+)|);
($F4)=($line=~m|F4\s*=\s*([\d.eEdD\Q-\E\Q+\E]+)|);
($F6)=($line=~m|F6\s*=\s*([\d.eEdD\Q-\E\Q+\E]+)|);
($B)=($line=~m|B\s*=\s*([\d.eEdD\Q-\E\Q+\E]+)|);
($C)=($line=~m|C\s*=\s*([\d.eEdD\Q-\E\Q+\E]+)|);
($xi)=($line=~m|xi\s*=\s*([\d.eEdD\Q-\E\Q+\E]+)|);
($flg3d)=($line=~m|flg3d\s*=\s*([\d.eEdD\Q-\E\Q+\E]+)|);
($flgBC)=($line=~m|flgBC\s*=\s*([\d.eEdD\Q-\E\Q+\E]+)|);
($l)=($line=~m|l\s*=\s*([\d.eEdD\Q-\E\Q+\E]+)|);
($n)=($conf=~m|.([\d.eEdD\Q-\E\Q+\E]+)|);
unless($l){$l=3;}
print "conf=$conf n=$n l=$l F2=$F2 F4=$F4 F6=$F6 B=$B C=$C xi=$xi flg3d=$flg3d flgBC=$flgBC\n";

 if($flg3d) # 3d ion - "xi" is actually lambda parameter -> |lambda| = xi/2S
   {
      $S2 = ($n<=(2*$l+1)) ? $n : ((4*$l+2)-$n);     # Finds 2S (maximise S from Hund's Rules)
     print "2*S=$S2\n"; 
     $xi *= $S2; 
   }
     
  if($flgBC) # Need to convert B and C parameters to F^2, F^4 slater integrals
   {
      # Eqn 77 of Racah II, A = F_0-49F_4 = F^0-F^4/9  B = F_2-5F_4 = (9F^2-5F^4)/441  C = 35F_4 = 5F^4/63 
    $F4= (63./5)*$C;  
    $F2 = (441.*$B+5*$F4)/9.; 
   }

   # converts from cm^-1 to meV
print "conf=$conf n=$n l=$l F2=$F2 F4=$F4 F6=$F6 B=$B C=$C xi=$xi flg3d=$flg3d flgBC=$flgBC\n";
   
        }
      }


      close Fin;


unless($confread){if($conf)
{   unless (open (Fin, $file)){die "\n error:unable to open $file\n";}   

   open (Fout, ">range.out");

   while($line=<Fin>)

     {print Fout $line;}

print Fout "\n\n\n\n#-------------------------------------------------------\n";
print Fout "conf=$conf\n";
print Fout "units=meV\n";
if ($xi){$xi/=8.06554486;print Fout "zeta=$xi\n";}
if ($F2){$F2/=8.06554486;print Fout "F2=$F2\n";}
if ($F4){$F4/=8.06554486;print Fout "F4=$F4\n";}
if ($F6){$F6/=8.06554486;print Fout "F6=$F6\n";}
print Fout << "EOF";
# Trivalent Lanthanides, from Carnall et al. J. Chem. Phys. v90, pp3443, 1989. All values in cm^-1, obtained from RE3+:LaCl_3
# Trivalent Actinides, from Carnall et al. J. Chem. Phys. v90, pp3443, 1989. All values in cm^-1, from An3+:LaCl_3 and An3+:LaF_3
# pa4+, cm3+ gd3+ parameters from Sytsma et al., Phys. Rev. B, v52, pp12668, 1995, in cm^-1, obtained from An4+:LuPO_4
# Tetravalent Actinides parameters from Poirot et al., Phys. Rev. B, v39, pp6388, 1989, in cm^-1, obtained from An4+:ZrSiO_4
# All d-electron parameters from Appendix of AS Chakravarty, Introduction to Magnetic Properties of Solid, Wiley, 1980. Original work also cited
# 3d ions parameters from JS Grifiths, The Theory of Transition Metal Ions, CUP, 1961 (A and B)  lambda from TM Dunn, Trans. Faraday Soc. v57, 1441 (1961)
# lambda_experimental -[from M Blume and RE Watson, Proc. R. Soc. Lon. A v271, 565 (1963)]
# 4d ions parameters from Richardson, Blackman and Ranschak, J. Chem. Phys. v58, 3010 (1973).
#   xi from calculations of Blume, Freeman, Watson, Phys. Rev. v134, A320 (1964), or where not calculated from TM Dunn, Trans. Faraday Soc. v57, 1441 (1961)
# 5d ions parameters from G Burns, J. Chem. Phys. v41, 1521 (1964) B,C only.
EOF
print Fout "\n\n\n\n#-------------------------------------------------------\n";
 

      close Fin;

      close Fout;

       unless (rename "range.out",$file)
     {unless(open (Fout, ">$file"))     
      {die "\n error:unable to write to $file\n";}
      open (Fin, "range.out");
      while($line=<Fin>){ print Fout $line;}
      close Fin;
      close Fout;
      system "del range.out"; 
     }


   }}

}


# **********************************************************************************************
# extracts variable from file
# 
# for example somewhere in a file data.dat is written the text "sta=0.24"
# to extract this number 0.24 just use:         
#
# ($standarddeviation)=extract("sta","data.dat");
# 
# ... it stores 0.24 in the variable $standarddeviation
#
sub extract { $value="";
             my ($variable,$filename)=@_;
             $var="\Q$variable\E";
             if(open (Fin,$filename))
             {while($line=<Fin>){
                if($line=~/^.*$var\s*=/) {($value)=($line=~m|$var\s*=\s*([\d.eEdD\Q-\E\Q+\E]+)|);}                                        }
              close Fin;
       	     }
             else
             {
             print STDERR "Warning: failed to read data file \"$filename\"\n";exit(1);
             }
             return $value;
            }
# **********************************************************************************************
