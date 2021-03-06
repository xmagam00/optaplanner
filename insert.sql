insert into organization(nameOfOrganization) values('Red Hat');
insert into organization(nameOfOrganization) values('IBM');
insert into organization(nameOfOrganization) values('Oracle');

insert into user(username,password,role,email,organization) values('martin','martin','Administrator','martin@martin.cz',1);
insert into user(username,password,role,email,organization) values('david','david','Reader','david@david.cz',2);
insert into user(username,password,role,email,organization) values('peter','peter','Planner','peter@peter.cz',3);

insert into task(xmlFile,stateOfTask,progressOfTask,ifpublic,eta,name,user,organization) values('<NQueens id="1">
  <id>0</id>
  <n>4</n>
  <columnList id="2">
    <Column id="3">
      <id>0</id>
      <index>0</index>
    </Column>
    <Column id="4">
      <id>1</id>
      <index>1</index>
    </Column>
    <Column id="5">
      <id>2</id>
      <index>2</index>
    </Column>
    <Column id="6">
      <id>3</id>
      <index>3</index>
    </Column>
  </columnList>
  <rowList id="7">
    <Row id="8">
      <id>0</id>
      <index>0</index>
    </Row>
    <Row id="9">
      <id>1</id>
      <index>1</index>
    </Row>
    <Row id="10">
      <id>2</id>
      <index>2</index>
    </Row>
    <Row id="11">
      <id>3</id>
      <index>3</index>
    </Row>
  </rowList>
  <queenList id="12">
    <Queen id="13">
      <id>0</id>
      <column reference="3"/>
    </Queen>
    <Queen id="14">
      <id>1</id>
      <column reference="4"/>
    </Queen>
    <Queen id="15">
      <id>2</id>
      <column reference="5"/>
    </Queen>
    <Queen id="16">
      <id>3</id>
      <column reference="6"/>
    </Queen>
  </queenList>
</NQueens>','NEW',0,0,120,'NQUEEN',2,1);
  insert into task(xmlFile,stateOfTask,progressOfTask,ifpublic,eta,name,user,organization) values('<NQueens id="1">
  <id>0</id>
  <n>4</n>
  <columnList id="2">
    <Column id="3">
      <id>0</id>
      <index>0</index>
    </Column>
    <Column id="4">
      <id>1</id>
      <index>1</index>
    </Column>
    <Column id="5">
      <id>2</id>
      <index>2</index>
    </Column>
    <Column id="6">
      <id>3</id>
      <index>3</index>
    </Column>
  </columnList>
  <rowList id="7">
    <Row id="8">
      <id>0</id>
      <index>0</index>
    </Row>
    <Row id="9">
      <id>1</id>
      <index>1</index>
    </Row>
    <Row id="10">
      <id>2</id>
      <index>2</index>
    </Row>
    <Row id="11">
      <id>3</id>
      <index>3</index>
    </Row>
  </rowList>
  <queenList id="12">
    <Queen id="13">
      <id>0</id>
      <column reference="3"/>
    </Queen>
    <Queen id="14">
      <id>1</id>
      <column reference="4"/>
    </Queen>
    <Queen id="15">
      <id>2</id>
      <column reference="5"/>
    </Queen>
    <Queen id="16">
      <id>3</id>
      <column reference="6"/>
    </Queen>
  </queenList>
</NQueens>','COMPLETE',0,0,140,'NQUEEN',3,2);
      insert into task(xmlFile,stateOfTask,progressOfTask,ifpublic,eta,name,user,organization) values('<NQueens id="1">
  <id>0</id>
  <n>4</n>
  <columnList id="2">
    <Column id="3">
      <id>0</id>
      <index>0</index>
    </Column>
    <Column id="4">
      <id>1</id>
      <index>1</index>
    </Column>
    <Column id="5">
      <id>2</id>
      <index>2</index>
    </Column>
    <Column id="6">
      <id>3</id>
      <index>3</index>
    </Column>
  </columnList>
  <rowList id="7">
    <Row id="8">
      <id>0</id>
      <index>0</index>
    </Row>
    <Row id="9">
      <id>1</id>
      <index>1</index>
    </Row>
    <Row id="10">
      <id>2</id>
      <index>2</index>
    </Row>
    <Row id="11">
      <id>3</id>
      <index>3</index>
    </Row>
  </rowList>
  <queenList id="12">
    <Queen id="13">
      <id>0</id>
      <column reference="3"/>
    </Queen>
    <Queen id="14">
      <id>1</id>
      <column reference="4"/>
    </Queen>
    <Queen id="15">
      <id>2</id>
      <column reference="5"/>
    </Queen>
    <Queen id="16">
      <id>3</id>
      <column reference="6"/>
    </Queen>
  </queenList>
</NQueens>','MODIFIED',0,0,150,'NQUEEN',1,3);

      insert into task(xmlFile,stateOfTask,progressOfTask,ifpublic,eta,name,user,organization) values('<NQueens id="1">
  <id>0</id>
  <n>4</n>
  <columnList id="2">
    <Column id="3">
      <id>0</id>
      <index>0</index>
    </Column>
    <Column id="4">
      <id>1</id>
      <index>1</index>
    </Column>
    <Column id="5">
      <id>2</id>
      <index>2</index>
    </Column>
    <Column id="6">
      <id>3</id>
      <index>3</index>
    </Column>
  </columnList>
  <rowList id="7">
    <Row id="8">
      <id>0</id>
      <index>0</index>
    </Row>
    <Row id="9">
      <id>1</id>
      <index>1</index>
    </Row>
    <Row id="10">
      <id>2</id>
      <index>2</index>
    </Row>
    <Row id="11">
      <id>3</id>
      <index>3</index>
    </Row>
  </rowList>
  <queenList id="12">
    <Queen id="13">
      <id>0</id>
      <column reference="3"/>
    </Queen>
    <Queen id="14">
      <id>1</id>
      <column reference="4"/>
    </Queen>
    <Queen id="15">
      <id>2</id>
      <column reference="5"/>
    </Queen>
    <Queen id="16">
      <id>3</id>
      <column reference="6"/>
    </Queen>
  </queenList>
</NQueens>','IN_PROGRESS',0,0,150,'NQUEEN',1,3);

      insert into task(xmlFile,stateOfTask,progressOfTask,ifpublic,eta,name,user,organization) values('<NQueens id="1">
  <id>0</id>
  <n>4</n>
  <columnList id="2">
    <Column id="3">
      <id>0</id>
      <index>0</index>
    </Column>
    <Column id="4">
      <id>1</id>
      <index>1</index>
    </Column>
    <Column id="5">
      <id>2</id>
      <index>2</index>
    </Column>
    <Column id="6">
      <id>3</id>
      <index>3</index>
    </Column>
  </columnList>
  <rowList id="7">
    <Row id="8">
      <id>0</id>
      <index>0</index>
    </Row>
    <Row id="9">
      <id>1</id>
      <index>1</index>
    </Row>
    <Row id="10">
      <id>2</id>
      <index>2</index>
    </Row>
    <Row id="11">
      <id>3</id>
      <index>3</index>
    </Row>
  </rowList>
  <queenList id="12">
    <Queen id="13">
      <id>0</id>
      <column reference="3"/>
    </Queen>
    <Queen id="14">
      <id>1</id>
      <column reference="4"/>
    </Queen>
    <Queen id="15">
      <id>2</id>
      <column reference="5"/>
    </Queen>
    <Queen id="16">
      <id>3</id>
      <column reference="6"/>
    </Queen>
  </queenList>
</NQueens>','WAITING',0,0,150,'NQUEEN',1,3);

      insert into task(xmlFile,stateOfTask,progressOfTask,ifpublic,eta,name,user,organization) values('<NQueens id="1">
  <id>0</id>
  <n>4</n>
  <columnList id="2">
    <Column id="3">
      <id>0</id>
      <index>0</index>
    </Column>
    <Column id="4">
      <id>1</id>
      <index>1</index>
    </Column>
    <Column id="5">
      <id>2</id>
      <index>2</index>
    </Column>
    <Column id="6">
      <id>3</id>
      <index>3</index>
    </Column>
  </columnList>
  <rowList id="7">
    <Row id="8">
      <id>0</id>
      <index>0</index>
    </Row>
    <Row id="9">
      <id>1</id>
      <index>1</index>
    </Row>
    <Row id="10">
      <id>2</id>
      <index>2</index>
    </Row>
    <Row id="11">
      <id>3</id>
      <index>3</index>
    </Row>
  </rowList>
  <queenList id="12">
    <Queen id="13">
      <id>0</id>
      <column reference="3"/>
    </Queen>
    <Queen id="14">
      <id>1</id>
      <column reference="4"/>
    </Queen>
    <Queen id="15">
      <id>2</id>
      <column reference="5"/>
    </Queen>
    <Queen id="16">
      <id>3</id>
      <column reference="6"/>
    </Queen>
  </queenList>
</NQueens>','MODIFIED',0,0,150,'NQUEEN',1,3);

      insert into task(xmlFile,stateOfTask,progressOfTask,ifpublic,eta,name,user,organization) values('<NQueens id="1">
  <id>0</id>
  <n>4</n>
  <columnList id="2">
    <Column id="3">
      <id>0</id>
      <index>0</index>
    </Column>
    <Column id="4">
      <id>1</id>
      <index>1</index>
    </Column>
    <Column id="5">
      <id>2</id>
      <index>2</index>
    </Column>
    <Column id="6">
      <id>3</id>
      <index>3</index>
    </Column>
  </columnList>
  <rowList id="7">
    <Row id="8">
      <id>0</id>
      <index>0</index>
    </Row>
    <Row id="9">
      <id>1</id>
      <index>1</index>
    </Row>
    <Row id="10">
      <id>2</id>
      <index>2</index>
    </Row>
    <Row id="11">
      <id>3</id>
      <index>3</index>
    </Row>
  </rowList>
  <queenList id="12">
    <Queen id="13">
      <id>0</id>
      <column reference="3"/>
    </Queen>
    <Queen id="14">
      <id>1</id>
      <column reference="4"/>
    </Queen>
    <Queen id="15">
      <id>2</id>
      <column reference="5"/>
    </Queen>
    <Queen id="16">
      <id>3</id>
      <column reference="6"/>
    </Queen>
  </queenList>
</NQueens>','PAUSED',0,0,150,'NQUEEN',1,3);


