#checkout fresh (if needed)
mkdir pages
cd pages
git clone git@github.com:ckaestne/fosd.net.git
cd fosd.net
git checkout gh-pages

#remove all previous files
find . -type f| grep -v '\.git' | xargs rm
cd ../..

#generate new page
cp -r src/site/* pages/fosd.net/

java LinkGen

cd pages/fosd.net

#commit
git add *
git commit -a -m "update website"
git push

#cleanup
#cd ../..
#rm -r -f pages
