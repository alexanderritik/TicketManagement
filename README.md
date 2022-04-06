# hu-22-java-track

ssh-add ~/.ssh/id_rsa_deloitte_github
<br/>
git clone git@github.com:Deloitte/hu-22-java-master.git
<br/>
cd hu-22-java-master
<br/>
git add <files>
<br/>
git commit -m "My commit message"
<br/>
git push origin main
<br/>

### You could also use below command to run the git commands along with ssh key
ssh-agent bash -c 'ssh-add ~/.ssh/id_rsa_deloitte_github; git clone git@github.com:Deloitte/hu-22-java-master.git'

## Run the below commands to start the application.
mvn clean install
<br/>
mvn clean spring-boot:run
