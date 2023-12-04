package com.example.mini.service.impl;

import com.example.mini.service.CodeDiffService;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Service
public class CodeDiffImpl implements CodeDiffService {
    public String gitlabUserName = "s1314233";
    public String gitlabPrivateToken = "duan3435";


    @Override
    public void cloneProject(String url, String branchName, String dir) {
        Git git = null;
        try {
            git = Git.cloneRepository()
                    .setCredentialsProvider(new UsernamePasswordCredentialsProvider(gitlabUserName, gitlabPrivateToken))
                    .setURI(url)
                    .setBranch(branchName)
                    .setDirectory(new File(dir))
                    .call();
            if (this.branchNameExist(git, branchName)) {
                //如果分支在本地已存在，直接checkout即可。
                git.checkout().setCreateBranch(false).setName(branchName).call();
            } else {
                //如果分支在本地不存在，需要创建这个分支，并追踪到远程分支上面。
                git.checkout().setCreateBranch(true).setName(branchName).setStartPoint("origin/" + branchName).call();
            }
        } catch (Exception e) {
            throw new RuntimeException("clone 项目失败" + url);
        } finally {
            if(git!=null) {
                git.close();
            }
        }
    }

    public boolean branchNameExist(Git git, String branchName) throws GitAPIException {
        List<Ref> refs = git.branchList().call();
        for (Ref ref : refs) {
            if (ref.getName().contains(branchName)) {
                return true;
            }
        }
        return false;
    }
}
